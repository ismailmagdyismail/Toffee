package Payment;
import Authorization.Authorizer;
import Authorization.EmailAuthorizer;
import Order.Order;
import Payment.PaymentExceptions.InvalidPayment;

public class CashPayment implements PaymentProcessor{
    private Order order;
    public CashPayment(Order order){
        this.order = order;
    }
    @Override
    public void pay() {
    }
}
