package business.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckoutStatusCareTaker implements Serializable {

    protected ArrayList<CheckoutStatusMemento> status;

    public CheckoutStatusCareTaker() {
        this.status = new ArrayList<CheckoutStatusMemento>();
    }

    public void addMemento(CheckoutStatusMemento memento) {
        this.status.add(memento);
    }

    public CheckoutStatusMemento getLastStatus() {
        if (this.status.size() <= 0) {
            return new CheckoutStatusMemento("PENDING UPLOAD");
        }
        CheckoutStatusMemento state = this.status.get(this.status.size() - 1);
        this.status.remove(this.status.size() - 1);
        return state;
    }
}
