package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class CleaningSupplies extends ItemDecorator {

    public CleaningSupplies(Item item){
        super(item);
        name = "Cleaning Supplies";
    }
}