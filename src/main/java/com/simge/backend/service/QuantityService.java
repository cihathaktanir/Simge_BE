package com.simge.backend.service;

import java.util.List;
import java.util.Map;

public interface QuantityService {
    Map<String, Integer> getStockBySkuListAndWarehouse(List<String> skuList, Integer warehouseNumber);
}
