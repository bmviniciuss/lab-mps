package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Snacks extends ItemDecorator {

    public Snacks(Item item){
        super(item);
        name = "Snacks";
    }
}