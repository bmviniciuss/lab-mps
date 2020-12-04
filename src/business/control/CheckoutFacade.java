package business.control;

import infra.CheckoutPersistence;
import util.*;

public class CheckoutFacade {

    private Invoker invoker;

    public CheckoutFacade(){
        invoker = new Invoker();
    }

    public void addCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new AddCommand());
    }

    public void updateCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new UpdateCommand());
    }

    public void searchCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.execute(new SearchCommand());
    }

    public void undoCheckout() throws UserNotFoundException, CheckoutNotFoundException, InfraException, RepeatedOrderIdException, OrderNotFoundException {
        invoker.undo(new UpdateCommand());
    }
}
