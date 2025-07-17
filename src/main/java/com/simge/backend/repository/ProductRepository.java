package com.simge.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simge.backend.model.Product;
import com.simge.backend.model.dto.product.ProductDTO;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

        @Query(value = "SELECT NEW com.simge.backend.model.dto.product.ProductDTO(" +
                        "p.recno, p.name, p.sku, p.unit, " +
                        "CAST(p.weight AS java.math.BigDecimal), " +
                        "CAST(b.brand_id AS string), b.brand, pr.price, " +
                        "NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, " +
                        "NULL, NULL, NULL, NULL, NULL, NULL, " +
                        "NULL) " +
                        "FROM Product p " +
                        "LEFT JOIN p.brand b " +
                        "LEFT JOIN Price pr ON p.sku = pr.productCode AND pr.warehouseNumber = :warehouseNumber AND pr.priceListNo = 1"
                        +
                        "WHERE (:recnoList IS NULL OR p.recno IN :recnoList)")
        Page<ProductDTO> findProductsWithPriceByWarehouseAndOptionalRecnoIn(
                        @Param("warehouseNumber") Integer warehouseNumber,
                        @Param("recnoList") List<Long> recnoList,
                        Pageable pageable);
}