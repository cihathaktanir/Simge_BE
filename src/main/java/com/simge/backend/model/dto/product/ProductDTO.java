package com.simge.backend.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    // STOKLAR tablosu ile doğrudan karşılık gelen alanlar:
    private Long id; // -> sto_RECno
    private String name; // -> sto_isim
    private String sku; // -> sto_kod
    private String unit; // -> sto_birim1_ad
    private BigDecimal weight; // -> sto_birim1_agirlik

    // Brand bilgileri (STOK_MARKALARI tablosundan)
    private String brand_id; // -> mrk_kod
    private String brand; // -> mrk_ismi

    // Price bilgileri (STOK_SATIS_FIYAT_LISTELERI tablosundan)
    private BigDecimal price; // -> sfiyat_fiyati (Ana liste fiyatı, örn: depo 1'den gelen)
    private BigDecimal sale_price; // -> sfiyat_fiyati (Kampanyalı veya indirimli fiyat için kullanılacak)

    // Backend'de karşılığı olmayan veya doğrudan Entity'lerde olmayan, ancak
    // frontend'in istediği alanlar:
    private String type = "simple"; // Frontend'den gelen sabit değer
    private Integer is_sale_enable; // Kampanya/indirim etkinliği
    private Integer discount; // İndirim oranı
    private Integer quantity; // Stok miktarı
    private String stock_status; // Stok durumu (in_stock/out_of_stock)
    private Integer safe_checkout; // Güvenli ödeme
    private List<String> attributes = new ArrayList<>(); // Özellikler
    private String slug; // URL dostu isim
    private List<String> related_products = new ArrayList<>(); // İlişkili ürünler
    private Integer status; // Ürünün genel durumu

    // Boş veya ayrı bir model/DTO olarak ele alınacak olanlar
    private List<Object> wholesales = new ArrayList<>();
    private List<Object> variations = new ArrayList<>();
    private List<Object> categories = new ArrayList<>();
    private List<Object> product_galleries = new ArrayList<>();
    private ProductThumbnail product_thumbnail;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductThumbnail {
        private String original_url;
    }

}
