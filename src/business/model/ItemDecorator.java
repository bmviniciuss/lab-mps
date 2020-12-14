package business.model;

public abstract class ItemDecorator extends Item {
    Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    public String getName(){
        return item.getName() + " - " + name;
    }
}