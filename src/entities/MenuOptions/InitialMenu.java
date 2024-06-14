package entities.MenuOptions;

import dbEntities.*;

import entities.patient.*;

import java.util.ArrayList;
import java.util.List;

import entities.patient.MedicalCare.*;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class InitialMenu {
    private int op;

    public static void runInitialMenu() {
        InitialMenu initialMenu = new InitialMenu();

        do {
            initialMenu.menu();
            initialMenu.options();
        } while (initialMenu.getOpInt() != 0);

        System.out.println("\nEncerrando programa...");
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Postinho ##\n");
        System.out.println("[1] : Registrar nova consultas.");
        System.out.println("[2] : Consultas agendados para hoje.");
        System.out.println("[3] : Atender consulta de hoje.");
        System.out.println("[4] : Cadastrar novos pacientes.");
        System.out.println("[5] : Acessar banco de pacientes(+).");
        System.out.println("[0] : Encerrar programa.");

        System.out.print("\nDigite uma das opções: ");
        op = ReadData.INT();
    }

    public int getOpInt() {
        return op;
    }

    public void options() {
        switch (op) {
            case 1:
                registerNewAppointment();
                break;
            case 2:
                appointmentRegisterToday();
                break;
            case 4:
                registerNewCleinte();
                break;
            case 5:
                DbMenu.runDbMenu();
                break;
            case 0:
                break;
            default:

                break;
        }
    }

    private void registerNewAppointment() {
        MedicalCare mCare = new MedicalCare();
        mCare.in();
        MedicalCareDAO.add(mCare);
    }

    private void appointmentRegisterToday() {
        Date dateToday = Date.inTerminal(true, "Digite os dados do dia de hoje:");

        List<MedicalCare> mcList = FutureMedicalCareDAO.seek(dateToday);

        Terminal.clear();
        if (mcList.size() == 0) {
            System.out.println("Não há consultas agendadas para hoje.");
        } else {
            System.out.println("-- Consultas para hoje --\n");

            for (MedicalCare mc : mcList) {
                System.out.println(mc + "\n");
            }
            System.out.println("Um total de " + mcList.size()
                    + " agendadas para hoje");
        }

        Terminal.pause();
    }

    private void registerNewCleinte() {
        Patient patientNew = new Patient();
        Terminal.clear();

        System.out.println("-- Cadastrando novo paciente no banco --\n");
        patientNew.in();

        PatientDAO.add(patientNew);
    }
}
