package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Health extends ItemDecorator {

    public Health(Item item){
        super(item);
        name = "Health and Beauty";
    }
}