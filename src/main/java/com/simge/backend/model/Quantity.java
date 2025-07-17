package com.simge.backend.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STOK_HAREKETLERI")
@Getter
@Setter
public class Quantity {
    @Id
    @Column(name = "sth_RECno")
    private Long recno;

    @Column(name = "sth_stok_kod")
    private String stokKod;

    @Column(name = "sth_miktar")
    private BigDecimal miktar;

    @Column(name = "sth_giris_depo_no")
    private Integer girisDepoNo;

    @Column(name = "sth_cikis_depo_no")
    private Integer cikisDepoNo;

}
