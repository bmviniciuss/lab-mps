package business.notification;

public class SMSNotifierAdapter implements NotificationAdapter {
    private SMSSender smsSender;
    public SMSNotifierAdapter() {
        this.smsSender = new SMSSender();
    }
    @Override
    public void notify(String contact, String message) {
        this.smsSender.send(contact, message);
    }
}
