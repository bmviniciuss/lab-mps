package view;

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
                        break;
                    default:
                        System.out.println("Operação não suportada");
                        break;
                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
