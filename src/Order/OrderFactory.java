package Order;

import Inventory.Product;
import Users.Buyer.Buyer;
import Users.Buyer.BuyerExceptions.EmptyCart;

import java.util.Map;

public class OrderFactory {
    private Buyer orderOwner;
    public OrderFactory(Buyer orderOwner){
            this.orderOwner = orderOwner;
    }
    public Order createOrder(){
        if(this.orderOwner.getCart().isEmpty()){
            throw new EmptyCart("Shopping Cart Is Currently Empty");
        }
        Order newOrder =  new Order(orderOwner,orderOwner.getCart().getProducts());
        return newOrder;
    }
}
