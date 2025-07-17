package com.simge.backend.model;

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
@Table(name = "STOK_MARKALARI")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @Column(name = "mrk_RECno")
    private Integer recno;

    @Column(name = "mrk_kod", unique = true)
    private String brand_id;

    @Column(name = "mrk_ismi")
    private String brand;
}
