// src/main/java/com/simge/backend/repository/BrandRepository.java
package com.simge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simge.backend.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}