package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Beverages extends ItemDecorator {

    public Beverages(Item item){
        super(item);
        name = "Beverage";
    }
}