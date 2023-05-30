package Inventory.InventoryExceptions;

public class InvalidProductQuantity extends RuntimeException{
    public InvalidProductQuantity(String msg){
        super(msg);
    }
}
