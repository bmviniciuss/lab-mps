package business.control;

import business.model.Checkout;
import business.model.OrderInterface;
import util.CheckoutNotFoundException;
import util.InfraException;

import java.util.Scanner;

public class UpdateCommand implements Command {

    ControllerFacade facade = ControllerFacadeFactory.getFacade();

    public void execute(CheckoutController controller) throws CheckoutNotFoundException, InfraException {

        Scanner reader = new Scanner(System.in);

        System.out.println("Insert checkout order id:");
        int id = Integer.parseInt(reader.next());
        Checkout checkout = controller.getCheckoutByOrderId(id);

        System.out.println("Insert new checkout status:");
        String status = reader.next();

        controller.updateStatus(checkout, status);
    }

    @Override
    public void undo() {

    }
}
