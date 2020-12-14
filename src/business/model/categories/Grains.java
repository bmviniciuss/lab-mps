package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class Grains extends ItemDecorator {

    public Grains(Item item){
        super(item);
        name = "Grains";
    }
}