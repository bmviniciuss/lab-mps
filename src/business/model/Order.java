package business.model;

import java.util.List;

public class Order implements OrderInterface {

    private int id;
    private IUser user;
    private List<ItemInterface> itemsList;
    private String status;

    public Order(int id, List<ItemInterface> itemsList){
        this.id = id;
        this.itemsList = itemsList;
        this.status = "PENDING";
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public IUser getUser() {
        return user;
    }

    public List<ItemInterface> getItemsList() {
        return itemsList;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getId() {
        return id;
    }
}
