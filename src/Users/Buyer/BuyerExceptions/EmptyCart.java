package Users.Buyer.BuyerExceptions;

public class EmptyCart extends RuntimeException{
    public EmptyCart(String msg){
        super(msg);
    }
}
