package com.simge.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "CARI_HESAPLAR")
@Getter
@Setter
public class CariHesap {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cari_RECno")
    private Long id;
    
    @Column(name = "cari_RECid_DBCno")
    private Integer recidDbcno;
    
    @Column(name = "cari_RECid_RECno")
    private Integer recidRecno;
    
    @Column(name = "cari_SpecRECno")
    private Integer specRecno;
    
    @Column(name = "cari_iptal")
    private Boolean iptal;
    
    @Column(name = "cari_fileid")
    private Integer fileId;
    
    @Column(name = "cari_hidden")
    private Boolean hidden;
    
    @Column(name = "cari_kilitli")
    private Boolean kilitli;
    
    @Column(name = "cari_degisti")
    private Boolean degisti;
    
    @Column(name = "cari_checksum")
    private Integer checksum;
    
    @Column(name = "cari_create_user")
    private Integer createUser;
    
    @Column(name = "cari_create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name = "cari_lastup_user")
    private Integer lastUpdateUser;
    
    @Column(name = "cari_lastup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Column(name = "cari_special1")
    private String special1;
    
    @Column(name = "cari_special2")
    private String special2;
    
    @Column(name = "cari_special3")
    private String special3;
    
    @Column(name = "cari_kod", nullable = false)
    private String cariKod;
    
    @Column(name = "cari_unvan1")
    private String cariUnvan1;
    
    @Column(name = "cari_unvan2")
    private String cariUnvan2;
    
    @Column(name = "cari_vdaire_adi")
    private String vergiDairesiAdi;
    
    @Column(name = "cari_vdaire_no")
    private String vergiDairesiNo;
    
    @Column(name = "cari_VergiKimlikNo")
    private String vergiKimlikNo;
    
    @Column(name = "cari_EMail")
    private String email;
    
    @Column(name = "cari_CepTel")
    private String cepTelefonu;
    
    @Column(name = "cari_efatura_fl")
    private Boolean efatura;
    
    @Column(name = "cari_eirsaliye_fl")
    private Boolean eirsaliye;
    
    @Column(name = "cari_kaydagiristarihi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kayitGirisTarihi;
    
    @Column(name = "cari_mersis_no")
    private String mersisNo;
    
    @Column(name = "cari_KEP_adresi")
    private String kepAdresi;
    
}
