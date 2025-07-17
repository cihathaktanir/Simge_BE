package com.simge.backend.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STOK_SATIS_FIYAT_LISTELERI")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @Column(name = "sfiyat_RECno")
    private Long recno;

    @Column(name = "sfiyat_stokkod")
    private String productCode; // İlişkili ürünün stok kodu (STOKLAR.sto_kod ile eşleşecek)

    @Column(name = "sfiyat_deposirano") // Depo sıra numarası (Depo filtresi için kullanılacak)
    private Integer warehouseNumber;

    @Column(name = "sfiyat_fiyati")
    private BigDecimal price; // Ürünün liste fiyatı

    @Column(name = "sfiyat_listesirano")
    private Integer priceListNo; // Ürünün liste fiyatı sırası
}
