package business.control;

import business.model.Checkout;
import util.*;

import java.util.Scanner;

public class UpdateCommand implements Command {

    public void execute() throws CheckoutNotFoundException, InfraException {
        CheckoutController controller = CheckoutControllerFactory.getController();
        Scanner reader = new Scanner(System.in);

        System.out.println("Insert checkout order id:");
        int id = Integer.parseInt(reader.next());
        Checkout checkout = controller.getCheckoutByOrderId(id);

        System.out.println("Insert new checkout status:");
        String status = reader.next();

        controller.updateStatus(checkout, status);
    }

    @Override
    public void undo() throws InfraException, CheckoutNotFoundException {
        CheckoutController controller = CheckoutControllerFactory.getController();

        Scanner reader = new Scanner(System.in);

        System.out.println("Insert checkout order id:");
        int id = Integer.parseInt(reader.next());
        Checkout checkout = controller.getCheckoutByOrderId(id);

        controller.undoStatus(checkout);
    }
}
