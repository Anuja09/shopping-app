package com.sm.inventory;

import com.sm.domains.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemsInventory implements Inventory {
    Map<String, Item> items = new HashMap<>();

    public ItemsInventory() {
        items.put("01001", new Item("01001", "Lemon", new BigDecimal(1.60), 5));
        items.put("01002", new Item("01002", "Apple", new BigDecimal(1.30), 5));
        items.put("01003", new Item("01003", "Mango", new BigDecimal(0.99), 5));
        items.put("01004", new Item("01003", "Mango", new BigDecimal(0.99), 0));
    }

    @Override
    public Optional<Item> get(String barcode) {
        if (null != items.get(barcode) && items.get(barcode).getAvailableStock() == 0) {
            throw new RuntimeException("Out Of Stock");
        }
        return Optional.ofNullable(items.get(barcode));
    }

    @Override
    public void updateStock(String barcode) {
        items.get(barcode).setAvailableStock(items.get(barcode).getAvailableStock() - 1);
    }

    @Override
    public void addBackToInventory(String barcode) {
        items.get(barcode).setAvailableStock(items.get(barcode).getAvailableStock() + 1);
    }
}
