package com.sm.domains;

import java.math.BigDecimal;

public class Item {
    String barcode;
    String name;
    BigDecimal price;
    int availableStock;

    public Item(String barcode, String name, BigDecimal price, int availableStock) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.availableStock = availableStock;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }
}
