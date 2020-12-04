package business.control;

import util.*;

public interface Command {

    public void execute() throws OrderNotFoundException, InfraException, UserNotFoundException, RepeatedOrderIdException, CheckoutNotFoundException;

    public  void undo() throws InfraException, CheckoutNotFoundException;

}
