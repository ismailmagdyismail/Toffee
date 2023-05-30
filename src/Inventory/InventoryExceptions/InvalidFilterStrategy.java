package Inventory.InventoryExceptions;

public class InvalidFilterStrategy extends RuntimeException{
    public InvalidFilterStrategy(String msg){
        super(msg);
    }
}
