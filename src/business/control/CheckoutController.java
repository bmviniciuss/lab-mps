package business.control;

import business.model.Checkout;
import infra.CheckoutPersistence;
import util.CheckoutNotFoundException;
import util.InfraException;
import util.RepeatedOrderIdException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutController implements Serializable {
    private List<Checkout> checkouts;
    private CheckoutPersistence checkoutPersistence;

    public CheckoutController(CheckoutPersistence checkoutPersistence) {
        this.checkouts = new ArrayList<>();
        this.checkoutPersistence = checkoutPersistence;
    }

    public void list() throws InfraException {
        this.checkouts = this.checkoutPersistence.load();

        for (Checkout checkout : checkouts) {
            System.out.println("#" + checkout.getOrder().getId() + " - " + checkout.getStatus());
        }
    }

    public Checkout listSingleCheckout(int id) throws InfraException, CheckoutNotFoundException {
        this.checkouts = this.checkoutPersistence.load();

        for (Checkout checkout : this.checkouts) {
            if(checkout.getOrder().getId() == id) {
                System.out.println("#" + checkout.getOrder().getId() + " - " + checkout.getStatus());
                return checkout;
            }
        }
        throw new CheckoutNotFoundException();
    }

    public Checkout getCheckoutByOrderId(int id) throws CheckoutNotFoundException, InfraException {
        this.checkouts = this.checkoutPersistence.load();

        for (Checkout checkout : this.checkouts) {
            if (checkout.getOrder().getId() == id) {
                return checkout;
            }
        }
        throw new CheckoutNotFoundException();
    }

    public void add(Checkout toCreateCheckout) throws InfraException, RepeatedOrderIdException {
        this.checkouts = this.checkoutPersistence.load();
        for (Checkout checkout : this.checkouts) {
            if (checkout.getOrder().getId() == toCreateCheckout.getOrder().getId()) {
                throw new RepeatedOrderIdException();
            }
        }
        this.checkouts.add(toCreateCheckout);
        this.checkoutPersistence.save(checkouts);
    }

    public void updateStatus(Checkout checkout, String status) throws InfraException{
        checkout.setStatus(status);
        this.checkoutPersistence.save(checkouts);
    }
}
