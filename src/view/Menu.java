package view;

import business.control.UserController;
import business.exceptions.UserLoginValidationException;
import business.exceptions.UserPasswordValidationException;
import business.model.User;

import java.util.Scanner;

public class Menu {
    private String menuChooseOptionText() {
        String s = "\n# Escolha a opção:\n";
        s += "\t1 - Criar usuário\n";
        s += "\t0 - Sair\n";
        return s;
    }

    public void run() {

        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                System.out.println(this.menuChooseOptionText());

                int op = reader.nextInt();

                switch (op) {
                    case 0:
                        reader.close();
                        System.out.println("Saindo da aplicação...");
                        return;
                    case 1:
                        System.out.println("Criar novo usuário");
                        System.out.println("Insira o login:");
                        String login = reader.next();
                        System.out.println("Insira senha: ");
                        String password = reader.next();
                        User toCreateUser = new User(login, password);
                        new UserController().add(toCreateUser);
                        break;
                    default:
                        System.out.println("Operação não suportada");
                        break;
                }


            } catch (UserLoginValidationException | UserPasswordValidationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
