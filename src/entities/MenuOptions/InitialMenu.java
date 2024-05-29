package entities.MenuOptions;

import entities.patient.CPF;
import entities.patient.MedicalCare.MedicalCare;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class InitialMenu implements InterfaceMenuOptions {
    private static String key = "Inicial";
    private int op;

    public void menu() {
        Terminal.clear();
        System.out.println("## Postinho ##");
        System.out.println("[1] : Registrar nova consultas.");
        System.out.println("[2] : Consultas agendados para hoje.");
        System.out.println("[3] : Atender consulta de hoje.");
        System.out.println("[4] : Cadastrar novos pacientes.");
        System.out.println("[5] : Acessar banco de pacientes(+).");
        System.out.println("[0] : Encerrar programa.");

        System.out.print("\nDigite uma das opções: ");
        op = ReadData.INT();
    }

    public void options() {
        switch (op) {
            case 1:
                registerNewCare();
                break;
            case 4:

                break;
            case 0:
                insertioNewCleinte();
                break;
            default:

                break;
        }
    }

    public String getMenu() {
        if (op == 5)
            return "BancoDados";
        return key;
    }

    private void registerNewCare() {
        MedicalCare mCare = new MedicalCare();
        mCare.in();

    }

    private void insertioNewCleinte() {

    }
}
