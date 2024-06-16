package entities.MenuOptions;

import entities.patient.Patient;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class PatientMenu {
    private int op;
    private Patient patient;

    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    public static void runPatientMenu(Patient patient) {
        PatientMenu pMenu = new PatientMenu(patient);

        do {
            pMenu.menu();
            pMenu.options();
        } while (pMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Paciente : " + patient.getName() + " ##\n");

        System.out.println("Dados do paciente:\n" + patient + "\n");

        System.out.println("[1] : Imprimir ultimas consultas.");
        System.out.println("[2] : Imprimir consultas futuras.");
        System.out.println("[3] : Remarcar consulta.");
        System.out.println("[4] : Atualizar consulta.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadData.INT();
    }

    public void options() {

    }

    public int getOp() {
        return op;
    }
}
