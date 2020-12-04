package infra;

public class CheckoutPersitenceFactory {
    public static CheckoutPersistence getPersistence() {
        return CheckoutPersistence.getInstance();
    }
}
