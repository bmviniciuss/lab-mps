package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Meats extends ItemDecorator {

    public Meats(Item item){
        super(item);
        name = "Meats";
    }
}