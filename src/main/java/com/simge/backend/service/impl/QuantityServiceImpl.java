package com.simge.backend.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.simge.backend.model.Quantity;
import com.simge.backend.repository.QuantityRepository;
import com.simge.backend.service.QuantityService;

@Service
public class QuantityServiceImpl implements QuantityService {
    private final QuantityRepository quantityRepository;

    public QuantityServiceImpl(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    @Override
    public Map<String, Integer> getStockBySkuListAndWarehouse(List<String> skuList, Integer warehouseNumber) {
        List<Quantity> hareketler = quantityRepository.findByStokKodInAndWarehouse(skuList, warehouseNumber);

        Map<String, BigDecimal> stockMap = new HashMap<>();

        for (Quantity h : hareketler) {
            String sku = h.getStokKod();
            BigDecimal miktar = h.getMiktar();
            int girisMi = Objects.equals(h.getGirisDepoNo(), warehouseNumber) ? 1 : 0;
            int cikisMi = Objects.equals(h.getCikisDepoNo(), warehouseNumber) ? 1 : 0;

            BigDecimal current = stockMap.getOrDefault(sku, BigDecimal.ZERO);
            current = current.add(miktar.multiply(BigDecimal.valueOf(girisMi - cikisMi)));
            stockMap.put(sku, current);
        }

        return stockMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().setScale(0, RoundingMode.DOWN).intValue()));
    }
}
