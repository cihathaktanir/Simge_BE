package com.simge.backend.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simge.backend.model.dto.product.ProductDTO;
import com.simge.backend.model.dto.product.ProductModelDTO;
import com.simge.backend.repository.ProductRepository;
import com.simge.backend.service.ProductService;
import com.simge.backend.service.QuantityService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QuantityService quantityService;

    public ProductServiceImpl(ProductRepository productRepository, QuantityService quantityService) {
        this.productRepository = productRepository;
        this.quantityService = quantityService;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductModelDTO getProducts(List<String> ids, Integer warehouseNumber, Pageable pageable) {

        List<Long> recnoList = null;
        if (ids != null && !ids.isEmpty()) {
            recnoList = ids.stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
        }

        Page<ProductDTO> productsPage = productRepository.findProductsWithPriceByWarehouseAndOptionalRecnoIn(
                warehouseNumber,
                recnoList,
                pageable);

        List<ProductDTO> products = productsPage.getContent();

        // SKU listesini oluştur
        List<String> skuList = products.stream()
                .map(ProductDTO::getSku)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // Stok miktarlarını bulk olarak al
        Map<String, Integer> stockMap = skuList.isEmpty()
                ? Collections.emptyMap()
                : quantityService.getStockBySkuListAndWarehouse(skuList, warehouseNumber);

        // DTO’lara quantity değerini set et
        products.forEach(p -> {
            Integer quantity = stockMap.get(p.getSku());
            p.setQuantity(quantity != null ? quantity : 0);
        });

        // Diğer alanları doldur
        products.forEach(this::fillProductDTOAdditionalFields);

        ProductModelDTO response = new ProductModelDTO();
        response.setData(products);
        response.setCurrentPage(productsPage.getNumber() + 1);
        response.setTotal((int) productsPage.getTotalElements());
        response.setPerPage(productsPage.getSize());
        response.setLastPage(productsPage.getTotalPages());
        response.setFrom((int) productsPage.getPageable().getOffset() + 1);
        response.setTo((int) productsPage.getPageable().getOffset() + products.size());

        response.setFirstPageUrl("");
        response.setLastPageUrl("");
        response.setNextPageUrl("");
        response.setPrevPageUrl("");
        response.setPath("");
        response.setLinks(new ArrayList<>());

        return response;
    }

    private void fillProductDTOAdditionalFields(ProductDTO productDTO) {
        if (productDTO.getSale_price() == null && productDTO.getPrice() != null) {
            productDTO.setSale_price(productDTO.getPrice());
        }
        if (productDTO.getDiscount() == null && productDTO.getPrice() != null && productDTO.getSale_price() != null) {
            if (productDTO.getPrice().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal discount = productDTO.getPrice().subtract(productDTO.getSale_price())
                        .divide(productDTO.getPrice(), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
                productDTO.setDiscount(discount.intValue());
            } else {
                productDTO.setDiscount(0);
            }
        }

        if (productDTO.getType() == null) {
            productDTO.setType("simple");
        }
        if (productDTO.getIs_sale_enable() == null) {
            productDTO.setIs_sale_enable(0);
        }
        if (productDTO.getQuantity() == null) {
            productDTO.setQuantity(0);
        }
        if (productDTO.getStock_status() == null) {
            if (productDTO.getQuantity() != null && productDTO.getQuantity() > 0) {
                productDTO.setStock_status("in_stock");
            } else {
                productDTO.setStock_status("out_of_stock");
            }
        }
        if (productDTO.getSafe_checkout() == null) {
            productDTO.setSafe_checkout(1);
        }
        if (productDTO.getSlug() == null) {
            productDTO
                    .setSlug(productDTO.getName() != null ? productDTO.getName().toLowerCase().replace(" ", "-") : "");
        }
        if (productDTO.getStatus() == null) {
            productDTO.setStatus(1);
        }

        if (productDTO.getAttributes() == null) {
            productDTO.setAttributes(new ArrayList<>());
        }
        if (productDTO.getRelated_products() == null) {
            productDTO.setRelated_products(new ArrayList<>());
        }
        if (productDTO.getWholesales() == null) {
            productDTO.setWholesales(new ArrayList<>());
        }
        if (productDTO.getVariations() == null) {
            productDTO.setVariations(new ArrayList<>());
        }
        if (productDTO.getCategories() == null) {
            productDTO.setCategories(new ArrayList<>());
        }
        if (productDTO.getProduct_galleries() == null) {
            productDTO.setProduct_galleries(new ArrayList<>());
        }

        if (productDTO.getProduct_thumbnail() == null) {
            ProductDTO.ProductThumbnail thumbnail = new ProductDTO.ProductThumbnail();
            thumbnail.setOriginal_url("assets/images/products/" + productDTO.getId() + ".jpg");
            productDTO.setProduct_thumbnail(thumbnail);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProductModelDTO getProductById(String id) {
        Long recno = Long.valueOf(id);
        Integer defaultWarehouseNumber = 1;

        Page<ProductDTO> productsPage = productRepository.findProductsWithPriceByWarehouseAndOptionalRecnoIn(
                defaultWarehouseNumber,
                Collections.singletonList(recno),
                PageRequest.of(0, 1));

        ProductDTO dto = productsPage.getContent().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id
                        + " or price data missing for warehouse " + defaultWarehouseNumber));

        fillProductDTOAdditionalFields(dto);

        ProductModelDTO response = new ProductModelDTO();
        response.setData(Collections.singletonList(dto));
        response.setCurrentPage(1);
        response.setTotal(1);
        response.setPerPage(1);
        response.setLastPage(1);
        response.setFrom(1);
        response.setTo(1);
        response.setLinks(new ArrayList<>());
        response.setFirstPageUrl("");
        response.setLastPageUrl("");
        response.setNextPageUrl("");
        response.setPrevPageUrl("");
        response.setPath("");

        return response;
    }
}
