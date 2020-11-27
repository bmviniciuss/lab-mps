package business.notification;

public class SMSSender {
    public void send(String number, String content) {
        System.out.println("Send SMS to " + number + " with " + content);
    }
}
