package business.control;

import business.model.Checkout;
import util.CheckoutNotFoundException;
import util.InfraException;

import java.util.Scanner;

public class SearchCommand  implements Command{

    public void execute() throws CheckoutNotFoundException, InfraException {
        CheckoutController controller = CheckoutControllerFactory.getController();

        Scanner reader = new Scanner(System.in);

        System.out.println("Insert checkout order id:");
        int id = Integer.parseInt(reader.next());
        Checkout checkout = controller.getCheckoutByOrderId(id);

        controller.listSingleCheckout(id);
    }

    @Override
    public void undo() {
        //
    }

}
