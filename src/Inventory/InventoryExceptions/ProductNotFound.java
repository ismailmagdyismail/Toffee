package Inventory.InventoryExceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String msg){
        super(msg);
    }
}
