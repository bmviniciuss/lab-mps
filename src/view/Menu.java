package view;

import business.control.UserController;
import util.InfraException;
import util.UserLoginValidationException;
import util.UserPasswordValidationException;
import business.model.User;
import infra.UserPersistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private String menuChooseOptionText() {
        String s = "\n# Escolha a opção:\n";
        s += "\t1 - Criar usuário\n";
        s += "\t2 - Listar usuários\n";
        s += "\t3 - Excluir usuário\n";
        s += "\t0 - Sair\n";
        return s;
    }

    public void run() {

        UserController controller;

        File f = new File("users.ser");

        try {
            if (f.exists()) {
                controller = UserPersistence.load();
            } else {
                controller = new UserController();
            }

            while (true) {
                Scanner reader = new Scanner(System.in);
                System.out.println(this.menuChooseOptionText());

                int op = reader.nextInt();

                switch (op) {
                    case 0:
                        reader.close();
                        UserPersistence.save(controller);
                        System.out.println("Saindo da aplicação...");
                        return;
                    case 1:
                        System.out.println("Criar novo usuário");
                        System.out.println("Insira o login:");
                        String login = reader.next();
                        System.out.println("Insira senha:");
                        String password = reader.next();
                        User toCreateUser = new User(login, password);
                        controller.add(toCreateUser);
                        UserPersistence.save(controller);
                        break;
                    case 2:
                        System.out.println("Usuários:");
                        controller.listAll();
                        break;
                    case 3:
                        System.out.println("Informe o login do usuário:");
                        String delete = reader.next();
                        controller.delete(delete);
                        UserPersistence.save(controller);
                        break;
                    default:
                        System.out.println("Operação não suportada.");
                        break;
                }
            }

        } catch (UserLoginValidationException | UserPasswordValidationException | InfraException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
