package business.model;

import java.util.List;

public class Order implements OrderInterface {

    private IUser user;
    private List<ItemInterface> itemsList;
    private String status;

    public Order(User user, List<ItemInterface> itemsList){
        this.user = user;
        this.itemsList = itemsList;
        this.status = "PENDING";
    }

    public IUser getUser() {
        return user;
    }

    public List<ItemInterface> getItemsList() {
        return itemsList;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
