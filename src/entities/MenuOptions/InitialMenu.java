package entities.MenuOptions;

import dbEntities.*;

import entities.patient.*;

import java.util.List;

import entities.patient.MedicalAppointment.*;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class InitialMenu {
    private int op;
    private Date today;

    public static void runInitialMenu() {
        InitialMenu initialMenu = new InitialMenu();

        do {
            initialMenu.menu();
            initialMenu.options();
        } while (initialMenu.getOpInt() != 0);

        System.out.println("\nEncerrando programa...");
    }

    public InitialMenu() {
        this.today = new Date();
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Postinho - Data: " + today + " ##\n");
        System.out.println("[1] : Registrar nova consultas.");
        System.out.println("[2] : Agender consulta.");
        System.out.println("[3] : Consultas agendados para hoje.");
        System.out.println("[4] : Atender consulta de hoje.");
        System.out.println("[5] : Cadastrar novos pacientes.");
        System.out.println("[6] : Acessar banco de pacientes(+).");
        System.out.println("[7] : Alterar data do dia atual.");
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
                scheduleNewAppointment();
                break;
            case 3:
                appointmentRegisterToday();
                break;
            case 4:
                assistPatientToday();
                break;
            case 5:
                registerNewCleinte();
                break;
            case 6:
                DbPatientMenu.runDbMenu();
                break;
            case 7:
                changeDate();
                break;
            case 0:
                break;
            default:

                break;
        }
    }

    private void registerNewAppointment() {
        MedicalAppointment mAppointment = MedicalAppointment.inTerminal(true, "## Insira os dados da consulta ##\n");
        MedicalAppointmentDAO.add(mAppointment);
    }

    private void scheduleNewAppointment() {
        MedicalAppointment mAppointment = MedicalAppointment.inTerminal(true, "## Insira os dados da consulta ##\n");
        FutureMedicalAppointmentDAO.add(mAppointment);
    }

    private void appointmentRegisterToday() {
        List<MedicalAppointment> mcList = FutureMedicalAppointmentDAO.seek(today);

        Terminal.clear();
        if (mcList.size() == 0) {
            System.out.println("Não há consultas agendadas para hoje.\n");
        } else {
            System.out.println("-- Consultas para hoje --\n");

            for (MedicalAppointment mc : mcList) {
                System.out.println(mc + "\n");
            }
            System.out.println("Um total de " + mcList.size()
                    + " agendadas para hoje.\n\n");
        }

        Terminal.pause();
    }

    private void assistPatientToday() {
        Terminal.clear();
        System.out.println("-- Atendendo consulta marcada para " + today + " --\n");
        System.out.print("Digite o Id da consulta: ");
        int id = ReadData.INT();
        MedicalAppointment mAppointment = FutureMedicalAppointmentDAO.seek(id);

        if (mAppointment == null) {
            System.out.println("\nId da consulta não encontrado.\n");
            Terminal.pause();
            return;
        }

        System.out.print("\nDeseja antender está consulta?\n\n" + mAppointment
                + "\n\n:[y][n]");
        if (ReadData.CHAR() == 'y') {
            FutureMedicalAppointmentDAO.delete(id);
            MedicalAppointmentDAO.add(mAppointment);

            System.out.println("Paciente atendido, seu id da consulta foi atualizado.\n\n");
        } else
            System.out.println("\n\nVoltando ao menu inicial.\n\n");
        Terminal.pause();
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

    private void changeDate() {
        this.today = Date.inTerminal(true, "Digite a nova data: ");
    }
}
