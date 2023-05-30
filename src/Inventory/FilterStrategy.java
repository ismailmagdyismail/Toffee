package Inventory;

import Inventory.Product;

import java.util.Collection;

public interface FilterStrategy {
    public Collection<Product> filterProducts(Collection<Product>products);
}
