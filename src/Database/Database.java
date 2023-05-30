package Database;

import Users.Profile.User;
import Inventory.Catalogue;

import java.util.Map;

public interface Database {
    public void addUser(User user);
    public User getUser(String userName,String Password);
    public Map<String, User> getUsers();
    public Catalogue getCatalogue();
}
