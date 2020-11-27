package util;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() {
        super("Order not found.");
    }
}
