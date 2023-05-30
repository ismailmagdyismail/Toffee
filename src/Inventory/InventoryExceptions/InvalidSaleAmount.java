package Inventory.InventoryExceptions;

public class InvalidSaleAmount extends RuntimeException{
    public InvalidSaleAmount(String msg){
        super(msg);
    }
}
