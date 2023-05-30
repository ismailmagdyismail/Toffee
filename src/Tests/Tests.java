package Tests;
import Users.Buyer.Address;
import Users.Buyer.Buyer;
import Users.Buyer.ShoppingCart;
import Users.Profile.Profile;
import Database.Database;
import Database.NonPersistentDatabase;
import Inventory.CategoryFilter;
import Inventory.Catalogue;
import Inventory.Product;
import Inventory.ProductCategory;
import Order.Order;
import Payment.CashPayment;
import Services.Checkout;
import Order.OrderFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Testing Classes Functionality
 * Testing Edge Cases
 * Testing Exceptions
 */
public class Tests {
    public static void main(String[] args){
        try {
            testAddress();
            testFiltering();
            testBuyer();
            testShoppingCart();
            testOrder();
            testOrderCreator();
            testCheckingOut();
            testDatabaseProducts();
            testDatabaseUsers();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void testFiltering(){
        Product product1 = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        Catalogue catalogue = new Catalogue();
        catalogue.addProduct(product1);
        catalogue.addProduct(product2);

        Collection<Product> collection = catalogue.filterProducts(new CategoryFilter(null));
        for (Product product : collection) {
            System.out.println(product.getCost());
            System.out.println("--------------------------------");
        }
    }
    public static void testShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();

        Product product = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        shoppingCart.addProduct(product,10);
        shoppingCart.addProduct(product2,20);

        Map<Product,Integer> cart = shoppingCart.getProducts();

        // testing adding products to cart
        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");

        // testing removing products to cart
        shoppingCart.decreaseProductCount(product,10);
        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");


        // testing Adding more than 50 Item
        shoppingCart.addProduct(product,42);

        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");

        //testing Clearing Products
        shoppingCart.clear();
        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");
    }
    public static void testOrder(){
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");
        System.out.println(address.getState());
        System.out.println(address.getCity());
        System.out.println(address.getStreet());
        System.out.println("==================================");

        Profile profile = new Profile("Ismail","semsem00882");

        Buyer buyer = new Buyer(profile,address);

        Product product = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        Map<Product,Integer> map = new HashMap<>();
        map.put(product,2);
        map.put(product2,2);

        Order order = new Order(buyer,map);

        System.out.println(order.getStatus());
        System.out.println(order.getTotalCost());
    }
    public static void testAddress(){
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");
        System.out.println(address.getState());
        System.out.println(address.getCity());
        System.out.println(address.getStreet());
    }
    public static void testBuyer(){
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");
        System.out.println(address.getState());
        System.out.println(address.getCity());
        System.out.println(address.getStreet());
        System.out.println("==================================");

        Profile profile = new Profile("ismailmagdy88@gmail.com","semsem00882");

        Buyer buyer = new Buyer(profile,address);

        // testing buyer personal data
        ShoppingCart shoppingCart = buyer.getCart();
        Product product = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        shoppingCart.addProduct(product,10);
        shoppingCart.addProduct(product2,20);

        Map<Product,Integer> cart = shoppingCart.getProducts();

        // testing adding products to cart
        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");

        // testing removing products to cart
        shoppingCart.decreaseProductCount(product,10);
        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");


        // testing Adding more than 50 Item
        shoppingCart.addProduct(product,42);

        for (Map.Entry<Product,Integer>entry : cart.entrySet()){
            System.out.println(entry.getKey().getCost() +" => "+entry.getValue());
        }
        System.out.println("==================================");

        Order order = new Order(buyer,cart);

        System.out.println(order.getStatus());
        System.out.println(order.getTotalCost());

        System.out.println("==================================");

        buyer.addOrder(order);
        Collection<Order> history = buyer.getOrderHistory();
        for (int i =0; i<history.size() ; i++){
            System.out.println(order.getID());
            System.out.println(order.getTotalCost());
            System.out.println(order.getStatus());
        }
    }
    public static void testOrderCreator(){
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");
        System.out.println(address.getState());
        System.out.println(address.getCity());
        System.out.println(address.getStreet());
        System.out.println("==================================");

        Profile profile = new Profile("ismailmagdy88@gmail.com","semsem00882");

        Buyer buyer = new Buyer(profile,address);

        ShoppingCart shoppingCart = new ShoppingCart();

        Product product = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        shoppingCart.addProduct(product,10);
        shoppingCart.addProduct(product2,20);

        Order order = new OrderFactory(buyer).createOrder();

        System.out.println(order.getStatus());
        System.out.println(order.getTotalCost());
    }
    public static void testCheckingOut(){
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");
        System.out.println(address.getState());
        System.out.println(address.getCity());
        System.out.println(address.getStreet());
        System.out.println("==================================");

        Profile profile = new Profile("ismailmagdy88@gmail.com","semsem00882");

        Buyer buyer = new Buyer(profile,address);


        Product product = new Product(null,null, ProductCategory.Chocolate,null,
                null,100,0);
        Product product2 = new Product(null,null, ProductCategory.Chocolate,null,
                null,1020,0);
        buyer.getCart().addProduct(product,10);
        buyer.getCart().addProduct(product2,20);

        Order order = new OrderFactory(buyer).createOrder();

        System.out.println(order.getStatus());
        System.out.println(order.getTotalCost());

        try {
            new Checkout(order,new CashPayment(order)).checkout();
        }
        catch (RuntimeException exception){
            System.out.println(exception.getMessage());
        }

        System.out.println(order.getStatus());
        System.out.println(order.getTotalCost());
    }
    public static void testDatabaseProducts(){
        Database database = new NonPersistentDatabase();
        Catalogue  catalogue = database.getCatalogue();
        Collection<Product> productList = catalogue.getProducts();
        for (Product product : productList){
            printProduct(product);
            System.out.println("==============");
        }
    }
    public static void testDatabaseUsers(){
        Database database = new NonPersistentDatabase();
        Address address = new Address("Giza","Hdayek-ElAhram","170-s");

        Profile profile = new Profile("ismailmagdy88@gmail.com","semsem00882");

        Buyer buyer = new Buyer(profile,address);

        database.addUser(buyer);

        Buyer buyer2 = new Buyer(profile,address);
        try {
            database.addUser(buyer2);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void printProduct(Product product){
        System.out.println(product.getName());
        System.out.println(product.getBrandName());
        System.out.println(product.getDescription());
        System.out.println(product.getSaleAmount());
        System.out.println(product.getCost());
        System.out.println(product.getType());
        System.out.println(product.getCategory());
    }
}
