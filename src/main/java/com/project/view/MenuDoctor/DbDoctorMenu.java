package com.project.view.MenuDoctor;

import java.sql.SQLException;

import com.project.model.Doctor;
import com.project.service.DoctorService;
import com.project.service.PatientService;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

public class DbDoctorMenu {
    private int op;

    public static void runDoctorMenu() {
        DbDoctorMenu menuDoctor = new DbDoctorMenu();
        do {
            menuDoctor.menu();
            menuDoctor.options();
        } while (menuDoctor.getOp() != 0);

        System.out.println("\nEncerrando programa...");
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Menu dos Doutores ##\n");

        System.out.println("[1] : Cadastrar novos doutores.");
        System.out.println("[2] : Buscar doutor por código.");
        System.out.println("[3] : Quantidade de doutores cadastrados.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                registerNewDoctor();
                break;
            case 2:
                searchDoctorByCoding();
                break;
            case 3:
                numberOfDoctorRegistered();
                break;
            default:
                break;
        }
    }

    private void registerNewDoctor() {
        Terminal.clear();
        System.out.println("-- Cadastrando novo doutor --\n");

        // System.out.println("Digite o CPF: ");
        // String cpf = ReadDataFromTerminal.STRING();

        System.out.print("Digite o nome: ");
        String name = ReadDataFromTerminal.STRING();

        try {
            DoctorService.save(name);
        } catch (SQLException e) {
            Terminal.clear();
            System.err.println("## ERRO ##");
            System.err.println("Ouvi um erro ao registrar um novo doutor.\n");
            Terminal.pause();
        }

    }

    private void searchDoctorByCoding() {
        Terminal.clear();
        System.out.println("-- Buscando Doutor --\n");
        System.out.print("Digite o código: ");
        int coding = ReadDataFromTerminal.INT();

        Doctor doctor = null;
        try {
            doctor = DoctorService.findById(coding);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("Ouvi um erro ao buscar o doutor.");
        }

        if (doctor == null) {
            System.out.println("paciente não encontrado.\n");
            Terminal.pause();
            return;
        }
        DoctorMenu.runDoctorMenu(doctor);
    }

    private void numberOfDoctorRegistered() {
        Terminal.clear();
        System.out.println("O posto tem um total de " + PatientService.cauntPatients()
                + " doutores cadastrados.\n");
        Terminal.pause();
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
}
