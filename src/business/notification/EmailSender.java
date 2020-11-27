package business.notification;

public class EmailSender {
    public void send(String email, String content) {
        System.out.println("Send email to " + email + " with " + content);
    }
}
