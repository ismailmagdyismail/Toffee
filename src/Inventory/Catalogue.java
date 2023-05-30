package Inventory;

import Inventory.InventoryExceptions.InvalidFilterStrategy;
import Inventory.InventoryExceptions.InvalidProductQuantity;
import Inventory.InventoryExceptions.ProductNotFound;

import java.util.*;

public class Catalogue {
    private Map<Product,Integer>inventory ;
    public Catalogue(){
        this.inventory = new HashMap<Product,Integer>();
    }
    public Collection<Product>getProducts(){
        Collection products = new ArrayList<Product>();
        for (Product product : inventory.keySet()){
            products.add(product);
        }
        return products;
    }
    public Integer getProductCount(Product product){
        Integer productCount = inventory.get(product);
        if (productCount == null){
            throw  new ProductNotFound("Product Not found In Stock");
        }
        return productCount;
    }
    public Map<Product,Integer> getInventory(){
        return this.inventory;
    }
    public Product getProduct(String productName){
        Set<Product>products = inventory.keySet();
        for (Product product : products){
            if(product.getName().equals(productName)){
                return product;
            }
        }
        throw new ProductNotFound("No Product Found with this name  ");
    }
    public void addProduct(Product product, int amount) throws RuntimeException{
        if(amount <= 0 )
            throw new RuntimeException("Invalid Product Count");
        inventory.put(product,amount);
    }
    public void addProduct(Product product) throws RuntimeException{
        Integer currentAmount = inventory.get(product);
        if(currentAmount == null)
            currentAmount = 0;
        inventory.put(product,currentAmount+1);
    }
    public void decreaseProductCount(Product product, int amount){
        Integer currentAmount = inventory.get(product);
        if(currentAmount == null){
            throw new ProductNotFound("This Product Doesn't Exist in The Inventory");
        }
        if(currentAmount < amount){
            throw new InvalidProductQuantity("Not enough Amount in Stock ,Only "+ currentAmount +" Available");
        }
        inventory.put(product,currentAmount - amount);
    }
    public void eraseProduct(Product product){
        if(inventory.get(product) == null){
            throw new ProductNotFound("This Product Doesn't Exist in The Inventory");
        }
        inventory.remove(product);
    }
    public Collection<Product> filterProducts(FilterStrategy filterStrategy){
        if(filterStrategy == null){
            throw new InvalidFilterStrategy("Filter Strategy is unspecified");
        }
        return filterStrategy.filterProducts(getProducts());
    }
}
