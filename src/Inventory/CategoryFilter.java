package Inventory;

import Inventory.FilterStrategy;
import Inventory.Product;
import Inventory.ProductCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CategoryFilter implements FilterStrategy {
    private ProductCategory filterCategory;
    public CategoryFilter(ProductCategory filterCategory){
        this.filterCategory = filterCategory;
    }
    @Override
    public Collection<Product> filterProducts(Collection<Product>products) {
        Iterator iterator = products.iterator();
        Collection<Product> filteredCollection = new ArrayList<>();
        while (iterator.hasNext()){
            Product product = (Product) iterator.next();
            if(filterCategory == null){
                filteredCollection.add(product);
                continue;
            }
            if(product.getCategory() == null){
                continue;
            }
            if( product.getCategory().equals(this.filterCategory) ){
                filteredCollection.add(product);
            }
        }
        return filteredCollection;
    }
}
