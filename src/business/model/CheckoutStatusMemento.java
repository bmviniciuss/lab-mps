package business.model;

import java.io.Serializable;

public class CheckoutStatusMemento implements Serializable {
    protected String status;

    public CheckoutStatusMemento(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
