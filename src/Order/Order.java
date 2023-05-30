package Order;
import Users.Buyer.Buyer;
import Inventory.Product;
import java.util.Map;

public class Order {
    private int id;
    private static int newID = 1 ;
    private OrderStatus status;
    private Buyer owner;
    private Map<Product,Integer>products;
    public Order(Buyer owner,Map<Product,Integer>products){
        this.owner =owner;
        this.id = newID;
        this.products = products;
        this.status = OrderStatus.Pending;
        Order.newID++;
    }
    public double getTotalCost(){
        double total = 0 ;
        for (Map.Entry<Product,Integer> entry: products.entrySet()){
            double productPrice = entry.getKey().getCost();
            int productCount = entry.getValue();
            double cost = productPrice * productCount ;
            total += cost;
        }
        return total;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public Map<Product,Integer> getProducts(){
        return this.products;
    }
    public int getID() {
        return id;
    }
    public Buyer getOwner() {
        return owner;
    }
}
