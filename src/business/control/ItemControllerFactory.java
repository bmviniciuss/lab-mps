package business.control;

public class ItemControllerFactory {
    public static ItemController getController() {
        return ItemController.getInstance();
    }
}
