package entities.MenuOptions;

import entities.patient.CPF;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class InitialMenu implements InterfaceMenuOptions {
    private int op;

    public void menu() {
        Terminal.clear();
        System.out.println("## Postinho ##");
        System.out.println("[1] : Registrar nova consultas.");
        System.out.println("[4] : Buscar paciente.");

        System.out.println("Digite uma das opções: ");
        op = ReadData.INT();
    }

    public void options() {
        switch (op) {
            case 1:
                registerNewCare();
                break;
            case 4:
                seekPatient();
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

    private void seekPatient() {
        CPF cpf = new CPF();
        cpf.in();

    }
}
