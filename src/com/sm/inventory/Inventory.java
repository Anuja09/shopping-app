package com.sm.inventory;

import com.sm.domains.Item;

import java.util.Optional;

public interface Inventory {
    Optional<Item> get(String barcode);

    void updateStock(String barcode);

    void addBackToInventory(String barcode);
}
