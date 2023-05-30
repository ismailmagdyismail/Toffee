package Users.Profile;

public class Profile {
    private int ID;
    private static int newID;
    private String email;
    private String password;
    public Profile(String email, String password){
        this.ID = newID;
        this.password = password;
        this.email = email;
        Profile.newID++;
    }
    public int getID() {
        return ID;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
