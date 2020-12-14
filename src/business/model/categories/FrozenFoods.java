package business.model.categories;

import business.model.Item;
import business.model.ItemDecorator;

public class FrozenFoods extends ItemDecorator {

    public FrozenFoods(Item item){
        super(item);
        name = "Frozen Foods";
    }
}