package com.simge.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simge.backend.model.CariHesap;
import com.simge.backend.repository.CariHesaplarRepository;
import com.simge.backend.service.CariHesaplarService;

@Service
public class CariHesaplarServiceImpl implements CariHesaplarService {

    private final CariHesaplarRepository repository;

    public CariHesaplarServiceImpl(CariHesaplarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CariHesap> getAllCariHesaplar() {
        return repository.findAll();
    }

    @Override
    public Optional<CariHesap> getCariHesapById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CariHesap saveCariHesap(CariHesap cariHesap) {
        return repository.save(cariHesap);
    }

    @Override
    public void deleteCariHesap(Long id) {
        repository.deleteById(id);
    }
}
