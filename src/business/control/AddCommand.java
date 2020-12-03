package business.control;

import business.model.Checkout;
import business.model.IUser;
import business.model.OrderInterface;
import util.InfraException;
import util.OrderNotFoundException;
import util.RepeatedOrderIdException;
import util.UserNotFoundException;

import java.util.Scanner;

public class AddCommand implements Command{

    ControllerFacade facade = ControllerFacadeFactory.getFacade();

    public void execute(CheckoutController controller) throws OrderNotFoundException, InfraException, UserNotFoundException, RepeatedOrderIdException {

        Scanner reader = new Scanner(System.in);

        System.out.println("Insert order id:");
        int id = Integer.parseInt(reader.next());
        OrderInterface order = facade.getOrderById(id);

        System.out.println("Insert username:");
        String name = reader.next();
        IUser user = facade.findUserByLogin(name);

        System.out.println("Insert receipt:");
        String receipt = reader.next();

        System.out.println("Insert price:");
        float price = Float.parseFloat(reader.next());

        Checkout checkout = new Checkout(order, user, receipt, price);

        controller.add(checkout);
    }

    public void undo(){
        //
    }
}
