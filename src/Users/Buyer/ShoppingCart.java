package Users.Buyer;
import Users.Buyer.BuyerExceptions.InvalidStockCount;
import Inventory.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product,Integer>cart ;
    private static int maxProductCount = 50;
    public ShoppingCart(){
       this.cart = new HashMap<Product,Integer>();
    }
    public Map<Product,Integer>getProducts(){
        return this.cart;
    }
    public void addProduct(Product product,int amount){
        Integer productCount = cart.get(product);
        if(productCount == null){
            productCount = 0;
        }
        if(productCount + amount > maxProductCount){
            throw new InvalidStockCount("Cannot Add more than 50 items / Kilos to Your cart");
        }
        this.cart.put(product,productCount + amount);
    }
    public void removeProduct(Product product){
        this.cart.remove(product);
    }
    public void decreaseProductCount(Product product,int amount){
        Integer productCount = cart.get(product);
        if(productCount == null){
            productCount = 0;
        }
        if(amount > productCount){
            throw new InvalidStockCount("There is only "+ productCount+ " items in your shopping cart");
        }
        else if(amount == productCount){
            this.cart.remove(product);
        }
        else{
            this.cart.put(product,productCount - amount);
        }
    }
    public boolean isEmpty(){
        return this.cart.isEmpty();
    }
    public void clear(){
        this.cart = new HashMap<>();
    }
}
