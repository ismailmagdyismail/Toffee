package Payment.PaymentExceptions;

public class InvalidPayment extends RuntimeException{
    public InvalidPayment(String msg){
        super(msg);
    }
}
