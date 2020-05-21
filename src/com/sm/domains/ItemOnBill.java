package com.sm.domains;

public class ItemOnBill {
    Item item;
    int quantity;

    public ItemOnBill(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }
}
