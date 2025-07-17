package com.simge.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "STOK_ALT_GRUPLARI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {

    @Id
    @Column(name = "sta_RECno")
    private Long recno;

    @Column(name = "sta_RECid_DBCno")
    private Long recidDbcno;

    @Column(name = "sta_RECid_RECno")
    private Long recidRecno;

    @Column(name = "sta_SpecRECno")
    private Long specRecno;

    @Column(name = "sta_iptal")
    private Boolean iptal;

    @Column(name = "sta_fileid")
    private Long fileid;

    @Column(name = "sta_hidden")
    private Boolean hidden;

    @Column(name = "sta_kilitli")
    private Boolean kilitli;

    @Column(name = "sta_degisti")
    private Boolean degisti;

    @Column(name = "sta_checksum")
    private String checksum;

    @Column(name = "sta_create_user")
    private String createUser;

    @Column(name = "sta_create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "sta_lastup_user")
    private String lastupUser;

    @Column(name = "sta_lastup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    @Column(name = "sta_special1")
    private String special1;

    @Column(name = "sta_special2")
    private String special2;

    @Column(name = "sta_special3")
    private String special3;

    @Column(name = "sta_kod")
    private String kod;

    @Column(name = "sta_isim")
    private String isim;

    @Column(name = "sta_ana_grup_kod")
    private String anaGrupKod;

    // Many-to-one relationship with Category
    @ManyToOne
    @JoinColumn(name = "sta_ana_grup_kod", referencedColumnName = "san_kod", insertable = false, updatable = false)
    private Category category;
}
