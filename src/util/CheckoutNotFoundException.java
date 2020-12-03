package util;

public class CheckoutNotFoundException extends Exception{
    public CheckoutNotFoundException() {
        super("Checkout not found.");
    }
}
