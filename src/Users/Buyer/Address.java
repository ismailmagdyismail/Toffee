package Users.Buyer;

public class Address {
    private String city;
    private String state;
    private String street;
    public Address(String state,String city ,String street){
        this.city = city;
        this.state = state;
        this.street = street;
    }
    public String getCity(){
        return this.city;
    }
    public String getState() {
        return state;
    }
    public String getStreet() {
        return street;
    }
}
