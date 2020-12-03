package business.control;

import infra.CheckoutPersistence;
import util.*;

public class CheckoutFacade {

    private Invoker invoker;

    public CheckoutFacade(){
        invoker = new Invoker();
    }

    public void addCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new CheckoutController(new CheckoutPersistence()), new AddCommand());
    }

    public void updateCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new CheckoutController(new CheckoutPersistence()), new UpdateCommand());
    }

    public void searchCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new CheckoutController(new CheckoutPersistence()), new SearchCommand());
    }
}
