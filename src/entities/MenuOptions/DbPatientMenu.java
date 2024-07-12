package entities.MenuOptions;

import dbEntities.PatientDAO;
import entities.patient.CPF;
import entities.patient.Patient;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class DbPatientMenu {
    private int op;

    public static void runDbMenu() {
        DbPatientMenu dbMenu = new DbPatientMenu();

        do {
            dbMenu.menu();
            dbMenu.options();
        } while (dbMenu.getOpInt() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Menu dos Pacientes ##\n");

        System.out.println("[1] : Cadastrar novos pacientes.");
        System.out.println("[2] : Buscar paciente por CPF.");
        System.out.println("[3] : Quantidade de pacientes cadastrados.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadData.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                registerNewCleinte();
                break;
            case 2:
                seekPatientForCPF();
                break;
            case 3:
                numberOfPatientRegistered();
                break;
            default:
                break;
        }
    }

    private void registerNewCleinte() {
        Terminal.clear();

        System.out.println("-- Cadastrando novo paciente no banco --\n");
        Patient patient = Patient.inTerminal(true, "Digite os dados do patiente:\n");

        if (!PatientDAO.existPatient(patient.geCpftId())) {
            PatientDAO.add(patient);
            return;
        }
        Terminal.clear();
        System.out.println("Desculpe, um paciente com o cpf: " + patient.geCpftId()
                + "já existe.\n\n");
        Terminal.pause();
    }

    private void seekPatientForCPF() {
        CPF cpf = CPF.inTerminal(true, "Digite os dados do CPF: ");

        Patient patient = PatientDAO.search(cpf);

        if (patient == null) {
            System.out.println("paciente não encontrado.\n");
            Terminal.pause();
            return;
        }
        PatientMenu.runPatientMenu(patient);
    }

    private void numberOfPatientRegistered() {
        Terminal.clear();
        System.out.println("O posto tem um total de " + PatientDAO.numberPatient()
                + " pacientes cadastrados.\n");
        Terminal.pause();
    }

    public int getOpInt() {
        return op;
    }
}
