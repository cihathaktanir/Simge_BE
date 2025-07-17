package com.simge.backend.service;

import java.util.List;
import java.util.Optional;

import com.simge.backend.model.CariHesap;

public interface CariHesaplarService {
    List<CariHesap> getAllCariHesaplar();
    Optional<CariHesap> getCariHesapById(Long id);
    CariHesap saveCariHesap(CariHesap cariHesap);
    void deleteCariHesap(Long id);
}
