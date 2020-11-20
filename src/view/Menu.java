package view;

import business.control.DateComparator;
import business.control.LoginComparator;
import business.control.UserController;
import business.model.Date;
import util.DateValidationException;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserPasswordValidationException;
import business.model.User;
import java.util.Scanner;

public class Menu {
    private String menuChooseOptionText() {
        String s = "\n# Choose Option:\n";
        s += "\t1 - Add User\n";
        s += "\t2 - List users by login\n";
        s += "\t3 - List users by birthdate\n";
        s += "\t4 - Remove User\n";
        s += "\t0 - Exit\n";
        return s;
    }

    private void validateDate(String date) throws DateValidationException {
        if(!(date.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"))) {
            throw new DateValidationException("Invalid date format.");
        }
    }

    public void run() {
        UserController controller = new UserController();
        try {
            while (true) {
                Scanner reader = new Scanner(System.in);
                System.out.println(this.menuChooseOptionText());

                int op = reader.nextInt();

                switch (op) {
                    case 0:
                        reader.close();
                        System.out.println("Exit...");
                        return;
                    case 1:
                        System.out.println("Create new user");
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
                        System.out.println("Users (sorted by login):");
                        controller.list(new LoginComparator());
                        break;
                    case 3:
                        System.out.println("Users (sorted by age):");
                        controller.list(new DateComparator());
                        break;
                    case 4:
                        System.out.println("User login:");
                        String delete = reader.next();
                        controller.delete(delete);
                        break;
                    default:
                        System.out.println("Not supported.");
                        break;
                }
            }

        } catch (UserLoginValidationException | UserPasswordValidationException | InfraException | DateValidationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
