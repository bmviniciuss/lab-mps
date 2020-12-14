package business.model;

public class Item implements ItemInterface {
    public String name;
    private int quantity;

    public Item (String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item () { }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }
}
