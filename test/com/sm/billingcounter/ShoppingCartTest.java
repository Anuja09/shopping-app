package com.sm.billingcounter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest {

    @Test
    public void shouldAddItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("01001");
        assertEquals(1, shoppingCart.getItems().size());

        shoppingCart.addItem("01002");
        shoppingCart.addItem("01001");
        assertEquals(2, shoppingCart.getItems().size());
        assertEquals(2, shoppingCart.getItems().get("01001").getQuantity());
        assertEquals(1, shoppingCart.getItems().get("01002").getQuantity());
    }

    @Test
    public void shouldThrowException_ItemOutOfStock() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThrows(RuntimeException.class, () -> shoppingCart.addItem("01004"));

        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        assertThrows(RuntimeException.class, () -> shoppingCart.addItem("01003"));
    }

    @Test
    public void shouldThrowException_ItemNotPresent() {
        ShoppingCart shoppingCart = new ShoppingCart();
        assertThrows(RuntimeException.class, () -> shoppingCart.addItem("01007"));
    }

    @Test
    public void shouldRemoveItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01002");
        shoppingCart.removeItem("01001");
        assertEquals(1, shoppingCart.getItems().size());

        shoppingCart.addItem("01001");
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01002");
        shoppingCart.addItem("01002");
        assertEquals(2, shoppingCart.getItems().get("01001").getQuantity());
        shoppingCart.removeItem("01001");
        assertEquals(1, shoppingCart.getItems().get("01001").getQuantity());
        assertEquals(3, shoppingCart.getItems().get("01002").getQuantity());
    }

    @Test
    public void shouldThrowException_ItemNotInCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01002");
        assertThrows(RuntimeException.class, () -> shoppingCart.removeItem("01008"));
    }

    @Test
    public void shouldReturnBillSummary() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01001");
        shoppingCart.addItem("01002");
        shoppingCart.addItem("01002");
        shoppingCart.addItem("01002");
        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        shoppingCart.addItem("01003");
        StringBuilder summary = shoppingCart.getBillSummary();
        System.out.println(summary);
        assertEquals(BigDecimal.valueOf(11.67), shoppingCart.getTotalBill());
    }

    @Test
    public void shouldReturnEmptyBillSummary() {
        ShoppingCart shoppingCart = new ShoppingCart();
        StringBuilder summary = shoppingCart.getBillSummary();
        System.out.println(summary);
        assertEquals(BigDecimal.valueOf(0), shoppingCart.getTotalBill());
    }
}
