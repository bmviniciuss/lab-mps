package business.control;

import util.*;

public class Invoker {

    public void execute(CheckoutController controller, Command command) throws UserNotFoundException, CheckoutNotFoundException, InfraException, OrderNotFoundException, RepeatedOrderIdException {
            command.execute(controller);
    }

}
