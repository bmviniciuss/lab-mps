package business.control;

import util.*;

public class Invoker {

    public void execute(Command command) throws UserNotFoundException, CheckoutNotFoundException, InfraException, OrderNotFoundException, RepeatedOrderIdException {
            command.execute();
    }

}
