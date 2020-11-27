package infra;

public class OrderPersintenceFactory {
    public static OrderPersistence getPersistence() {
        return OrderPersistence.getInstance();
    }
}
