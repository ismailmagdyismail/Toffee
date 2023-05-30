package Services;

import Database.Database;
import Inventory.Product;
import Order.Order;
import Order.OrderStatus;
import Payment.PaymentProcessor;

import java.util.Map;

public class Checkout {
    private Order order;
    private PaymentProcessor paymentMethod;
    public Checkout(Order order, PaymentProcessor paymentMethod){
        this.order = order;
        this.paymentMethod = paymentMethod;
    }
    public void checkout(){
        try {
            paymentMethod.pay();
            updateDatabaseInventory();
            this.order.setStatus(OrderStatus.OutToDelivery);
            order.getOwner().getCart().clear();
        }
        catch (RuntimeException runtimeException){
            throw runtimeException;
        }
    }
    private void updateDatabaseInventory(){
        Map<Product,Integer>products = order.getProducts();
        for (Map.Entry<Product,Integer> entry: products.entrySet()){
            Product product = entry.getKey();
            int productCount = entry.getValue();
            ApplicationManger.database.getCatalogue().decreaseProductCount(product,productCount);
        }
    }
}
