package entities.MenuOptions;

import java.util.List;

import dbEntities.FutureMedicalAppointmentDAO;
import entities.patient.Patient;
import entities.patient.MedicalAppointment.MedicalAppointment;
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
        switch (this.op) {
            case 1:
                printAppointmentPatient();
                break;
            case 2:
                printFutureAppointmentPatient();
                break;
            default:
                break;
        }
    }

    private void printAppointmentPatient() {
        Terminal.clear();
        List<MedicalAppointment> list = this.patient.getmedicalAppointmentsList();

        if (list.size() == 0) {
            System.out.println("Não há consultas realizadas para este paciente.\n");
        } else {
            System.out.println("Consultas realizadas pelo paciente: " + this.patient.getName() + "\n");

            for (MedicalAppointment mAppointment : list) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void printFutureAppointmentPatient() {
        Terminal.clear();
        List<MedicalAppointment> list = FutureMedicalAppointmentDAO.seek(this.patient.geCpftId());

        if (list.size() == 0) {
            System.out.println("Não há consultas agendadas para este paciente.\n");
        } else {
            System.out.println("Consultas marcadas pelo paciente: " + this.patient.getName() + "\n");

            for (MedicalAppointment mAppointment : list) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    public int getOp() {
        return op;
    }
}
