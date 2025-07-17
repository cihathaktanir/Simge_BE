package com.simge.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STOKLAR")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "sto_RECno")
    private Long recno; // Ürünün benzersiz kayıt numarası/kimliği

    @Column(name = "sto_isim")
    private String name; // Ürünün adı

    @Column(name = "sto_kod", unique = true) // Ürünün stok kodu, Price tablosunda da kullanılacak
    private String sku; // Stok kodu

    @Column(name = "sto_birim1_ad")
    private String unit; // Ürün birimi

    @Column(name = "sto_birim1_agirlik")
    private BigDecimal weight; // Ürünün ağırlığı

    // Brand ile Many-to-One ilişkisi
    // STOKLAR.sto_marka_kodu alanı ile STOK_MARKALARI.mrk_kod alanı arasında
    // bağlantı kurulur.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sto_marka_kodu", referencedColumnName = "mrk_kod")
    private Brand brand;

    // Price için entity ilişkisi kurmuyoruz, çünkü her ürünün birden çok fiyatı var
    // ve seçilen depoya göre fiyat farklı olabiliyor.
    @Transient
    private BigDecimal price;

    @Column(name = "sto_create_user")
    private String createUser;

    @Column(name = "sto_create_date")
    private LocalDateTime createDate;

    @Column(name = "sto_lastup_user")
    private String lastupUser;

    @Column(name = "sto_lastup_date")
    private LocalDateTime lastupDate;
}