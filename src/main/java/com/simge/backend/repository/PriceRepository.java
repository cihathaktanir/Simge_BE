package com.simge.backend.repository;

import com.simge.backend.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    // Belirli bir ürün kodu (productCode) ve depo numarasına (warehouseNumber) göre fiyatları çekmek için
    List<Price> findByProductCodeAndWarehouseNumber(String productCode, Integer warehouseNumber);

    // Belirli ürün kodlarına (productCode) ve depo numarasına göre fiyatları çekmek için
    List<Price> findByProductCodeInAndWarehouseNumber(List<String> productCodes, Integer warehouseNumber);
}