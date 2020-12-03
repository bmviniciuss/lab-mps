package business.control;

import util.*;

public interface Command {

    public void execute(CheckoutController controller) throws OrderNotFoundException, InfraException, UserNotFoundException, CheckoutNotFoundException, RepeatedOrderIdException;

    public  void undo();

}
