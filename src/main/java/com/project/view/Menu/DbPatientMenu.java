package com.project.view.Menu;

import com.project.entity.Cpf;
import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.dao.PatientDAO;

public class DbPatientMenu {
    private int op;

    public static void runDbPatientMenu() {
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
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                registerNewCleinte();
                break;
            case 2:
                searchPatientForCPF();
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
        Patient patient = new Patient();

        System.out.println("-- Cadastrando novo paciente --\n");

        System.out.println("Digite o CPF: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.println("Digite o nome: ");
        String name = ReadDataFromTerminal.STRING();

        try {
            patient.setCpfId(new Cpf(cpf));
            patient.setName(name);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        }

        if (PatientDAO.existPatient(patient.geCpft())) {
            PatientDAO.add(patient);
            return;
        }
        Terminal.clear();
        System.out.println("Desculpe, um paciente com o cpf: " + patient.geCpft()
                + "já existe.\n\n");
        Terminal.pause();
    }

    private void searchPatientForCPF() {
        Cpf cpf = null;

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
