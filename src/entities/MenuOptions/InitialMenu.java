package entities.MenuOptions;

import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class InitialMenu implements InterfaceMenuOptions {
    private int op;

    public void menu() {
        Terminal.clear();
        System.err.println("## Postinho ##");
        System.err.println("[1] : Registrar nova consultas.");

        System.err.println("Digite uma das opções: ");
        op = ReadData.INT();
    }

    public void options() {
        switch (op) {
            case 1:
                registerNewCare();
                break;
            case 0:
                insertioNewCleinte();
                break;
            default:
                break;
        }
    }

    public int getOp() {
        return op;
    }

    private void registerNewCare() {

    }

    private void insertioNewCleinte() {

    }
}
