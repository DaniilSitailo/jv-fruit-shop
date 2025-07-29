package core.basesyntax;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private final Map<String, Integer> inventory;

    public Storage() {
        this.inventory = new HashMap<>();
    }

    public void setBalance(String fruit, int quantity) {
        inventory.put(fruit, quantity);
    }

    public void supply(String fruit, int quantity) {
        inventory.put(fruit, inventory.getOrDefault(fruit, 0) + quantity);
    }

    public void purchase(String fruit, int quantity) {
        int currentQuantity = inventory.getOrDefault(fruit, 0);
        if (currentQuantity < quantity) {
            throw new IllegalArgumentException("Not enough " + fruit + " in stock");
        }
        inventory.put(fruit, currentQuantity - quantity);
    }

    public void returnFruits(String fruit, int quantity) {
        inventory.put(fruit, inventory.getOrDefault(fruit, 0) + quantity);
    }

    public Map<String, Integer> getInventory() {
        return new HashMap<>(inventory); // Return a copy to prevent external modification
    }
}
