package util;

public class RepeatedOrderIdException extends Exception{
    public RepeatedOrderIdException() {
        super("A checkout for this order already exists.");
    }
}
