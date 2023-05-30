package Database.DatabaseExceptions;

public class UsedUserName extends RuntimeException{
    public UsedUserName(String msg){
        super(msg);
    }
}
