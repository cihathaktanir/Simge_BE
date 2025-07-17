package com.simge.backend.model;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STOK_ANA_GRUPLARI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "san_RECno")
    private Long recno;

    @Column(name = "san_RECid_DBCno")
    private Long recidDbcno;

    @Column(name = "san_RECid_RECno")
    private Long recidRecno;

    @Column(name = "san_SpecRECno")
    private Long specRecno;

    @Column(name = "san_iptal")
    private Boolean iptal;

    @Column(name = "san_fileid")
    private Long fileid;

    @Column(name = "san_hidden")
    private Boolean hidden;

    @Column(name = "san_kilitli")
    private Boolean kilitli;

    @Column(name = "san_degisti")
    private Boolean degisti;

    @Column(name = "san_checksum")
    private String checksum;

    @Column(name = "san_create_user")
    private String createUser;

    @Column(name = "san_create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "san_lastup_user")
    private String lastupUser;

    @Column(name = "san_lastup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    @Column(name = "san_special1")
    private String special1;

    @Column(name = "san_special2")
    private String special2;

    @Column(name = "san_special3")
    private String special3;

    @Column(name = "san_kod")
    private String kod;

    @Column(name = "san_isim")
    private String isim;

    // One-to-many relationship with SubCategory
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<SubCategory> subCategories;
}
