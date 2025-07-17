package com.simge.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simge.backend.model.CariHesap;

@Repository
public interface CariHesaplarRepository extends JpaRepository<CariHesap, Long> {
}
