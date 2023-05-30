package Authorization;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Random;

public class EmailAuthorizer implements Authorizer{
    private final String sender = "ismailmagdy88@gmail.com";
    private final String password = "nkorimvluoymvbhm";
    private final String host = "smtp.gmail.com";
    private final int port = 587 ;
    private final String subject = "OTP Authorization";
    private final String OTP = Integer.toString(100 + new Random().nextInt(8001));

    private final String body = "This is OTP for Your Order From Toffee Application\n" +
            "OTP IS : "+OTP;
    private String receiver ;
    public EmailAuthorizer(String receiver){
        this.receiver = receiver;
    }
    @Override
    public void sendOTP() {
      // Properties properties = System.getProperties();
       Properties properties = new Properties();
       properties.put("mail.smtp.auth","true");
       properties.put("mail.smtp.starttls.enable","true");
       properties.put("mail.smtp.host",host);
       properties.put("mail.smtp.port",port);


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void isAuthorized(String code) {
        if(!code.equals(OTP)){
            throw  new RuntimeException("Code Doesn't Match OTP Sent to Your Email");
        }
    }
}



