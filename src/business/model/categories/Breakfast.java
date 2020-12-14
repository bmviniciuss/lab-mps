package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Breakfast extends ItemDecorator {

    public Breakfast(Item item){
        super(item);
        name = "Breakfast";
    }
}