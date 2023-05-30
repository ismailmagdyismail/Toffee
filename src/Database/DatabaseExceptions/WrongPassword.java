package Database.DatabaseExceptions;

public class WrongPassword extends RuntimeException{
    public WrongPassword(String msg){
        super(msg);
    }
}
