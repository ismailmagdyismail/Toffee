package Authorization;

public interface Authorizer {
    public void sendOTP();
    public void isAuthorized(String code);
}
