package business.model;

import java.io.Serializable;

public class Checkout implements Serializable {
    private OrderInterface order;
    private IUser user;
    private String receipt;
    private float price;
    private String status;

    public Checkout (OrderInterface order, IUser user, String receipt, float price) {
        this.order = order;
        this.user = user;
        this.receipt = receipt;
        this.price = price;
        this.status = "PENDING UPLOAD";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public OrderInterface getOrder() {
        return order;
    }
}
