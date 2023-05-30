package Database;

import Users.Buyer.Address;
import Users.Buyer.Buyer;
import Users.Profile.Profile;
import Users.Profile.User;
import Database.DatabaseExceptions.UsedUserName;
import Database.DatabaseExceptions.UserNotFound;
import Database.DatabaseExceptions.WrongPassword;
import Inventory.Catalogue;
import Inventory.Product;
import Inventory.ProductCategory;
import Inventory.ProductType;

import java.util.HashMap;
import java.util.Map;

public class NonPersistentDatabase implements Database{
    private Map<String,User> users;
    private Catalogue catalogue;
    public NonPersistentDatabase(){
        users = new HashMap<String,User>();
        catalogue = new Catalogue();

        Product product1 = new Product("Crunchy Caramel Bar", "Sweet Treats", ProductCategory.Toffee, ProductType.Sealed, "A delicious bar with a crunchy caramel center.", 2.99, 10);
        Product product2 = new Product("Fruit Chewies", "Nature's Best", ProductCategory.Candy, ProductType.Sealed, "A bag of fruity chewy candies.", 1.49, 0);
        Product product3 = new Product("Dark Chocolate Squares", "Divine Delights", ProductCategory.Chocolate, ProductType.Sealed, "A box of rich and creamy dark chocolate squares.", 4.99, 20);
        Product product4 = new Product("Salted Caramel Truffles", "Choco-Luxe", ProductCategory.Chocolate, ProductType.Sealed, "A box of indulgent salted caramel truffles.", 6.99, 15);
        Product product5 = new Product("Gummy Bears", "Happy Treats", ProductCategory.Candy, ProductType.Sealed, "A bag of colorful gummy bears.", 1.99, 5);
        Product product6 = new Product("Peanut Butter Cups", "Nutty Delights", ProductCategory.Chocolate, ProductType.Sealed, "A package of creamy peanut butter cups.", 3.49, 0);
        Product product7 = new Product("Fruity Lollipops", "Rainbow Sweets", ProductCategory.Candy, ProductType.Sealed, "A pack of assorted fruity lollipops.", 0.99, 0);
        Product product8 = new Product("Toffee Crunch Bites", "Crispy Treats", ProductCategory.Toffee, ProductType.Sealed, "A bag of toffee bites with a crispy texture.", 2.49, 10);
        Product product9 = new Product("Milk Chocolate Bar", "Choco Heaven", ProductCategory.Chocolate, ProductType.Sealed, "A classic milk chocolate bar.", 2.99, 0);
        Product product10 = new Product("Sour Fruit Gummies", "Tart Treats", ProductCategory.Candy, ProductType.Sealed, "A bag of sour fruit-flavored gummy candies.", 1.99, 5);

        catalogue.addProduct(product1,50);
        catalogue.addProduct(product2,50);
        catalogue.addProduct(product3,50);
        catalogue.addProduct(product4,50);
        catalogue.addProduct(product5,50);
        catalogue.addProduct(product6,30);
        catalogue.addProduct(product7,50);
        catalogue.addProduct(product8,50);
        catalogue.addProduct(product9,50);
        catalogue.addProduct(product10,50);

        Address address = new Address("Giza","Hdayek-ElAhram","170-s");


        Profile profile = new Profile("ismailmagdy88@gmail.com","semsem00882");

        Buyer buyer = new Buyer(profile,address);
        addUser(buyer);
    }
    @Override
    public void addUser(User user) {
        String userName = user.getProfile().getEmail();
        if(users.get(userName) != null){
            throw new UsedUserName("This Email is already Used");
        }
        this.users.put(userName,user);
    }
    @Override
    public User getUser(String userName, String password) {
        User user = users.get(userName);
        if(user == null){
            throw new UserNotFound("This Email doesn't Exist");
        }
        String storedPassword = user.getProfile().getPassword();
        if(!password.equals(storedPassword)){
            throw new WrongPassword("Entered Password Doesn't match Existing password");
        }
        return user;
    }
    public Map<String, User> getUsers() {
        return users;
    }
    public Catalogue getCatalogue() {
        return catalogue;
    }
}
