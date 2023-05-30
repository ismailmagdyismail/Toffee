package Inventory.InventoryExceptions;

public class InvalidProductPrice extends RuntimeException{
    public InvalidProductPrice(String msg){
        super(msg);
    }
}
