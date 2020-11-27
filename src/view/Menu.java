package view;

import business.control.DateComparator;
import business.control.LoginComparator;
import business.control.OrderController;
import business.control.UserController;
import business.model.Date;
import business.model.Order;
import infra.OrderPersistence;
import infra.UserPersistence;
import util.*;
import business.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String menuChooseOptionText() {
        String s = "\n# Choose Option:\n";
        s += "\t1 - Add User\n";
        s += "\t2 - List users by login\n";
        s += "\t3 - List users by birthdate\n";
        s += "\t4 - Search user\n";
        s += "\t5 - Remove User\n";
        s += "\t6 - List Orders by status\n";
        s += "\t0 - Exit\n";
        return s;
    }

    private void validateDate(String date) throws DateValidationException {
        if(!(date.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"))) {
            throw new DateValidationException("Invalid date format.");
        }
    }

    public void run() {
        UserController controller = new UserController(new UserPersistence());
        OrderController orderController = new OrderController(new OrderPersistence());

        Order order1 = new Order(new ArrayList<>());
        Order order2 = new Order(new ArrayList<>());
        Order order3 = new Order(new ArrayList<>());

        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println(this.menuChooseOptionText());
                int op = reader.nextInt();
                switch (op) {
                    case 0:
                        // exit
                        reader.close();
                        System.out.println("Exit...");
                        return;
                    case 1:
                        // create new user
                        System.out.println("# Create User");

                        System.out.println("Login:");
                        String login = reader.next();

                        System.out.println("Password:");
                        String password = reader.next();

                        System.out.println("Birthdate (DD/MM/AAAA):");
                        String date = reader.next();

                        this.validateDate(date);
                        Date birth_date = new Date(date);

                        User toCreateUser = new User(login, password, birth_date);
                        controller.add(toCreateUser);
                        break;
                    case 2:
                        // List users by login
                        System.out.println("# List Users (sorted by login):");
                        controller.list(new LoginComparator());
                        break;
                    case 3:
                        // List users by age
                        System.out.println("# List Users (sorted by age):");
                        controller.list(new DateComparator());
                        break;
                    case 4:
                        // Search User
                        System.out.println("# Search User");
                        System.out.println("Login: ");
                        String toSearchLogin = reader.next();
                        controller.listSingleUser(toSearchLogin);
                        break;
                    case 5:
                        // Remover User
                        System.out.println("# Remove User");
                        System.out.println("Login: ");
                        String toDeleteLogin = reader.next();
                        controller.delete(toDeleteLogin);
                        System.out.println("User deleted successfully.");
                        break;
                    case 6:
                        //List orders by status

                        System.out.println("# List Orders (sorted by status):");
                        orderController.list();
                        break;
                    default:
                        System.out.println("Not supported.");
                        break;
                }

            } catch (UserLoginValidationException | UserPasswordValidationException | InfraException | DateValidationException | UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
