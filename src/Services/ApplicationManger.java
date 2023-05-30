package Services;

import Authorization.Authorizer;
import Authorization.EmailAuthorizer;
import Database.Database;
import Database.NonPersistentDatabase;
import Inventory.CategoryFilter;
import Inventory.Product;
import Inventory.ProductCategory;
import Order.OrderFactory;
import Payment.CashPayment;
import Users.Buyer.Address;
import Users.Buyer.Buyer;
import Order.Order;
import Users.Profile.Profile;
import Users.Profile.User;

import java.util.*;

public abstract class ApplicationManger {
    public static void run(){
        initialize();
        while (true){
            displayMainMenu();
            int mainMenuChoice = getMainMenuChoice();
            executeMainMenuChoice(mainMenuChoice);
            boolean authorized = loggedInBuyer != null;
            while (authorized){
                displayOptions();
                int optionsChoice = getOptionsChoice();
                executeOptionChoice(optionsChoice);
                authorized = loggedInBuyer != null;
            }
        }
    }
    private static void initialize(){
        loggedInBuyer = null;
        database = new NonPersistentDatabase();
    }
    private static void login(){
        System.out.println("-------------------------Logging In-------------------------");
        Scanner scanner = new Scanner(System.in);
        String userName ;
        String password ;
        System.out.println("Enter Email:");
        userName = scanner.nextLine();
        System.out.println("Enter Password:");
        password = scanner.nextLine();
        try {
            loggedInBuyer = database.getUser(userName,password);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    private static void signup(){
        System.out.println("-------------------------Signing Up-------------------------");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("-------------------------Personal Info-------------------------");

            System.out.println("Enter Email (VALID EMAIL):");
            String email = scanner.nextLine();

            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            Profile profile  = new Profile(email,password);

            System.out.println("-------------------------Address Info-------------------------");

            System.out.println("Enter State Name:");
            String state = scanner.nextLine();

            System.out.println("Enter City Name");
            String city = scanner.nextLine();

            System.out.println("Enter Street Name");
            String street = scanner.nextLine();

            Address address = new Address(state,city,street);

            User buyer = new Buyer(profile,address);
            database.addUser(buyer);
            loggedInBuyer = buyer;

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    private static void displayMainMenu(){
        System.out.println("-------------------------Authorization-------------------------");
        System.out.println("1-Login");
        System.out.println("2-Sign-Up");
    }
    private static int  getMainMenuChoice(){
        System.out.println("Enter your Choice :");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private static void executeMainMenuChoice(int choice){
        if(choice == Login){
            login();
        }
        else if(choice == Signup){
            signup();
        }
    }
    private static void displayOptions(){
        System.out.println("-------------------------Options-------------------------");
        System.out.println("1-Display Catalogue Products");
        System.out.println("2-Add Product To Cart");
        System.out.println("3-Checkout");
        System.out.println("4-Logout");
    }
    private static int getOptionsChoice(){
        System.out.println("Enter your Choice :");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private static void executeOptionChoice(int choice){
        if(choice == DisplayCatalogue){
            displayCatalogueProducts();
        }
        else if(choice == AddProductsToCart){
            addProductToCart();
        }
        else if(choice == Checkout){
            checkOut();
        }
        else if(choice == Logout){
            logout();
        }
    }
    private static void displayCatalogueProducts(){
        System.out.println("-------------------------Filter Strategy-------------------------");
        System.out.println("1-display Candy");
        System.out.println("2-display Toffee");
        System.out.println("3-display Chocolate");
        System.out.println("4-display All ");
        System.out.println("Enter your Choice :");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Collection<Product>products ;
        try {
            if(choice == 1){
                products = database.getCatalogue().filterProducts(new CategoryFilter(ProductCategory.Candy));
            }
            else if(choice == 2){
                products = database.getCatalogue().filterProducts(new CategoryFilter(ProductCategory.Toffee));
            }
            else if(choice == 3){
                products = database.getCatalogue().filterProducts(new CategoryFilter(ProductCategory.Chocolate));
            }
            else if(choice == 4){
                products = database.getCatalogue().filterProducts(new CategoryFilter(null));
            }
            else{
                displayCatalogueProducts();
                return;
            }
            System.out.println("-------------------------Available Products-------------------------");
            for (Product product :products){
                displayProduct(product,database.getCatalogue().getProductCount(product));
                System.out.println("================================");
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    private static void displayProduct(Product product ,int productCount){
        System.out.println("Product-Name: "+product.getName());
        System.out.println("Brand Name: "+product.getBrandName());
        System.out.println("Product-Category: "+product.getCategory());
        System.out.println("Product-Type: "+product.getType());
        System.out.println("Product-Description: "+product.getDescription());
        System.out.println("Product-Price: "+product.getPrice());
        System.out.println("Product-saleAmount: "+product.getSaleAmount());
        System.out.println("Product-Cost After Discount: "+product.getCost());
        System.out.println("Available in Stock: "+productCount);
    }
    private static void addProductToCart(){
        System.out.println("-------------------------Adding Product To Cart-------------------------");
        System.out.println("Enter Product Name:");
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine();
        try {
            Product product = database.getCatalogue().getProduct(productName);
            System.out.println("Enter Amount to Add your Shopping Cart");
            int amount = scanner.nextInt();
            if(amount > database.getCatalogue().getProductCount(product)){
                System.out.println("Not Enough Items Available in Stock");
                return;
            }
            ((Buyer)loggedInBuyer).getCart().addProduct(product,amount);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
    private static void checkOut(){
        System.out.println("-------------------------Checking  out-------------------------");
        try {
            Order order = new OrderFactory((Buyer)loggedInBuyer).createOrder();
            Authorizer emailAuthorizer = new EmailAuthorizer(loggedInBuyer.getProfile().getEmail());
            emailAuthorizer.sendOTP();
            System.out.println("Enter OTP Sent to Your Email ("+loggedInBuyer.getProfile().getEmail()+"):");
            String OTP = new Scanner(System.in).nextLine();
            emailAuthorizer.isAuthorized(OTP);
            new Checkout(order,new CashPayment(order)).checkout();
            printOrderData(order);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    private static void printOrderData(Order order){
        System.out.println("-------------------------Order-Details-------------------------");
        Address address = order.getOwner().getAddress();
        System.out.println("Order ID: " + order.getID());
        printProducts(order.getProducts());
        System.out.println("Order Status: " + order.getStatus());
        System.out.println("Order Total-Cost: " + order.getTotalCost());
        System.out.println("Delivery Address:"+address.getState()+" , "+address.getCity()+" , "+address.getStreet());
    }
    private static void printProducts(Map<Product,Integer>products){
        for (Map.Entry<Product,Integer>entry:products.entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();
            System.out.println("Product Name:"+product.getName()+" , Cost/item:"+product.getCost()+
                    " , Quantity:"+count+" , TotalCost:"+count*product.getCost());
        }
    }
    private static void logout(){
        System.out.println("-------------------------Logging Out-------------------------");
        loggedInBuyer = null;
    }
    private static int Login = 1;
    private static int Signup = 2;
    private static int DisplayCatalogue = 1;
    private static int AddProductsToCart = 2;
    private static int Checkout = 3;
    private static int Logout = 4;
    static Database database ;
    static User loggedInBuyer;
}
