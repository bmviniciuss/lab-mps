package business.control;

import business.model.Checkout;
import util.CheckoutNotFoundException;
import util.InfraException;

import java.util.Scanner;

public class SearchCommand  implements Command{

    ControllerFacade facade = ControllerFacadeFactory.getFacade();

    public void execute(CheckoutController controller) throws CheckoutNotFoundException, InfraException {

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
