package business.control;

import business.model.Item;
import business.model.ItemInterface;

public class ItemController {
    private static ItemController itemController;

    private ItemController() { }

    public static ItemController getInstance() {
        if (itemController == null) {
            itemController = new ItemController();
        }
        return itemController;
    }

    public ItemInterface create(String name, int quantity) {
        return new Item(name, quantity);
    }
}
