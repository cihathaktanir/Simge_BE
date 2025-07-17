package com.simge.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simge.backend.model.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    @Query("FROM Quantity q WHERE q.stokKod IN :skuList AND " +
           "(q.girisDepoNo = :warehouseNumber OR q.cikisDepoNo = :warehouseNumber)")
    List<Quantity> findByStokKodInAndWarehouse(
            @Param("skuList") List<String> skuList,
            @Param("warehouseNumber") Integer warehouseNumber);
}
