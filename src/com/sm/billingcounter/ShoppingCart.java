package com.sm.billingcounter;

import com.sm.domains.Item;
import com.sm.domains.ItemOnBill;
import com.sm.inventory.Inventory;
import com.sm.inventory.ItemsInventory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author anuja
 */
public class ShoppingCart {
    private BigDecimal totalBill;
    private Map<String, ItemOnBill> items;
    private final Inventory inventory;

    public ShoppingCart() {
        totalBill = new BigDecimal("0");
        items = new HashMap<>();
        inventory = new ItemsInventory();
    }

    public void addItem(String barcode) {
        Optional<Item> item = inventory.get(barcode);
        item.orElseThrow(() -> new RuntimeException("Item not present in inventory"));

        Optional<Integer> quantity = Optional.empty();
        if (items.containsKey(barcode)) {
            quantity = Optional.of(items.get(barcode))
                    .map(ItemOnBill::getQuantity)
                    .map(qty -> qty + 1);
        }

        items.put(barcode, new ItemOnBill(item.get(), quantity.orElse(1)));
        inventory.updateStock(barcode);
    }

    public Map<String, ItemOnBill> getItems() {
        return items;
    }

    public void removeItem(String barcode) {
        if (!items.containsKey(barcode)) {
            throw new RuntimeException("Item is not added to cart");
        }

        if (items.get(barcode).getQuantity() > 1) {
            int quantity = Optional.of(items.get(barcode))
                    .map(ItemOnBill::getQuantity).get();
            items.get(barcode).setQuantity(quantity - 1);
        } else {
            items.remove(barcode);
        }
        inventory.addBackToInventory(barcode);
    }

    public StringBuilder getBillSummary() {
        Map<String, String> itemRow = new TreeMap<>();
        StringBuilder builder = new StringBuilder();
        for (String barcode : items.keySet()) {
            StringBuilder builderRow = new StringBuilder();
            ItemOnBill itemOnBill = items.get(barcode);
            BigDecimal pricePerItem = itemOnBill.getItem().getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal totalPrice = BigDecimal.valueOf(itemOnBill.getQuantity())
                    .multiply(pricePerItem);
            totalBill = totalBill.add(totalPrice);
            itemRow.put(itemOnBill.getItem().getName(), builderRow.append(itemOnBill.getQuantity())
                    .append(" X ")
                    .append(itemOnBill.getItem().getName())
                    .append(" @ ")
                    .append(pricePerItem)
                    .append(" = ").append(totalPrice).toString());
        }

        itemRow.forEach((itemName, itemDetails) -> builder.append(itemDetails).append("\n"));
        builder.append("Total = ").append(totalBill);

        return builder;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }
}
