package com.simge.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simge.backend.model.CariHesap;
import com.simge.backend.service.CariHesaplarService;

@RestController
@RequestMapping("/api/cari-hesaplar")
public class CariHesaplarController {

    private final CariHesaplarService service;

    public CariHesaplarController(CariHesaplarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CariHesap> getAllCariHesaplar() {
        return service.getAllCariHesaplar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CariHesap> getCariHesapById(@PathVariable Long id) {
        Optional<CariHesap> cariHesap = service.getCariHesapById(id);
        return cariHesap.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CariHesap> createCariHesap(@RequestBody CariHesap cariHesap) {
        CariHesap savedCariHesap = service.saveCariHesap(cariHesap);
        return ResponseEntity.ok(savedCariHesap);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCariHesap(@PathVariable Long id) {
        service.deleteCariHesap(id);
        return ResponseEntity.noContent().build();
    }
}
