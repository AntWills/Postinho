package entities.MenuOptions;

import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class DbMenu {
    private int op;

    public static void runDbMenu() {
        DbMenu dbMenu = new DbMenu();

        do {
            dbMenu.menu();
            dbMenu.options();
        } while (dbMenu.getOpInt() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Menu do Banco de Dados ##\n");

        System.out.println("[1] : Buscar paciente por CPF.");
        System.out.println("[2] : Quantidade de pacientes cadastrados.");
        System.out.println("[3] : Buscar consultas.");
        System.out.println("[0] : Voltar.");

        System.out.print("Digite uma das opções: ");
        this.op = ReadData.INT();
    }

    public void options() {

    }

    public int getOpInt() {
        return op;
    }
}
