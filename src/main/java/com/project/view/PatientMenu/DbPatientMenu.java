package com.project.view.PatientMenu;

import java.sql.SQLException;

import com.project.exception.InvalidCpfException;
import com.project.model.Patient;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.util.UtilCpf;
import com.project.service.PatientService;

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
                registerNewPatient();
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

    private void registerNewPatient() {
        Terminal.clear();

        System.out.println("-- Cadastrando novo paciente --\n");

        System.out.print("Digite o CPF: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.print("Digite o nome: ");
        String name = ReadDataFromTerminal.STRING();

        try {
            PatientService.save(cpf, name);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        } catch (Exception e) {
            Terminal.clear();
            System.err.println("Desculpe, um paciente com o cpf: " + cpf
                    + "já existe.\n\n");
            Terminal.pause();
        }

    }

    private void searchPatientForCPF() {
        Terminal.clear();
        System.out.println("-- Buscando Paciente --\n");
        System.out.print("Digite o CPF: ");
        String cpf = ReadDataFromTerminal.STRING();

        try {
            UtilCpf.validator(cpf);
        } catch (InvalidCpfException e) {
            System.out.println("## ERRO ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        }

        Patient patient = null;
        try {
            patient = PatientService.findById(cpf);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("- Ouvi um erro ao buscar o paciente.");
            Terminal.pause();
            return;
        }

        if (patient == null) {
            System.out.println("paciente não encontrado.\n");
            Terminal.pause();
            return;
        }
        PatientMenu.runPatientMenu(patient);
    }

    private void numberOfPatientRegistered() {
        Terminal.clear();
        System.out.println("O posto tem um total de " + PatientService.cauntPatients()
                + " pacientes cadastrados.\n");
        Terminal.pause();
    }

    public int getOpInt() {
        return op;
    }
}
