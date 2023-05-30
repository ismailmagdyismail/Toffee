package Users.Buyer.BuyerExceptions;

public class InvalidStockCount extends RuntimeException{
    public InvalidStockCount(String msg){
        super(msg);
    }
}
