package business.notification;

public class EmailNotifierAdapter implements NotificationAdapter {
    private EmailSender emailSender;
    public EmailNotifierAdapter() {
        this.emailSender = new EmailSender();
    }
    @Override
    public void notify(String contact, String message) {
        this.emailSender.send(contact, message);
    }
}
