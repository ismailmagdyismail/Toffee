package Users.Buyer;

import Users.Profile.Profile;
import Users.Profile.User;
import Order.Order;

import java.util.ArrayList;
import java.util.List;

public class Buyer implements User {
    private Profile profile ;
    private Address address;
    private List<Order> orderHistory;
    private ShoppingCart cart;
    public Buyer(Profile profile , Address address){
        if(profile == null ){
            throw new RuntimeException("Un-Specified Profile");
        }
        if(address == null){
            throw new RuntimeException("Un-Specified Address");
        }
        this.profile = profile;
        this.address = address;
        orderHistory = new ArrayList<Order>();
        cart = new ShoppingCart();
    }
    @Override
    public Profile getProfile() {
        return this.profile;
    }
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    public ShoppingCart getCart() {
        return this.cart;
    }
    public Address getAddress() {
        return address;
    }
    public void addOrder(Order order){
        this.orderHistory.add(order);
    }

}
