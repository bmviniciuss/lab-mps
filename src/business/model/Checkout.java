package business.model;

import java.io.Serializable;

public class Checkout implements Serializable {
    private OrderInterface order;
    private IUser user;
    private String receipt;
    private float price;
    private String status;
    private CheckoutStatusCareTaker checkoutStatusCareTaker;

    public Checkout (OrderInterface order, IUser user, String receipt, float price) {
        this.order = order;
        this.user = user;
        this.receipt = receipt;
        this.price = price;
        this.status = "PENDING UPLOAD";
        this.checkoutStatusCareTaker = new CheckoutStatusCareTaker();
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.checkoutStatusCareTaker.addMemento(new CheckoutStatusMemento(this.status));
        this.status = status;
    }

    public void undo() {
        this.status = this.checkoutStatusCareTaker.getLastStatus().getStatus();
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
