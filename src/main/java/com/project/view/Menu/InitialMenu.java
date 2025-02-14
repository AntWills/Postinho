package com.project.view.Menu;

import com.project.entity.*;
import com.project.exception.InvalidCpfException;
import com.project.exception.InvalidDateException;
import com.project.model.MedicalConsultation;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.dao.*;
import java.util.List;

public class InitialMenu {
    private int op;
    private Date today;

    public InitialMenu() {
        this.today = new Date();
    }

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
        System.out.println("## Postinho - Data: " + today + " ##\n");
        System.out.println("[1] : Registrar nova consultas.");
        System.out.println("[2] : Agender consulta.");
        System.out.println("[3] : Consultas agendados para hoje.");
        System.out.println("[4] : Atender consulta de hoje.");
        System.out.println("[5] : Acessar banco de consultas(+).");
        System.out.println("[6] : Acessar banco de pacientes(+).");
        System.out.println("[7] : Alterar data do dia atual.");
        System.out.println("[0] : Encerrar programa.");

        System.out.print("\nDigite uma das opções: ");
        op = ReadDataFromTerminal.INT();
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
                DbMedicalAppointmentMenu.runDbMedicalAppointmentMenu();
                break;
            case 6:
                DbPatientMenu.runDbPatientMenu();
                break;
            case 7:
                // changeDate();
                break;
            case 0:
                break;
            default:

                break;
        }
    }

    private void registerNewAppointment() {

        // MedicalConsultation mAppointment = MedicalConsultation.inTerminal(true, "##
        // Insira os dados da consulta ##\n");
        // MedicalAppointmentDAO.add(mAppointment);
    }

    private void scheduleNewAppointment() {

        System.out.println("Tipos de atendimento: ");
        System.out.println("(0) : [NOT URGENT] : Blue");
        System.out.println("(1) : [LITTLE URGENT] : Green");
        System.out.println("(2) : [URGENT] : Yellow");
        System.out.println("(3) : [EMERGING] : Red\n");

        System.out.print("Digite o tipo: ");
        int type = ReadDataFromTerminal.INT();

        System.out.println("Digite o CPF: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.println("Digite a data: ");
        String date = ReadDataFromTerminal.STRING();

        System.out.print("\nDigite a razão da consulta (Opcional): ");
        String reaso = ReadDataFromTerminal.STRING();

        MedicalConsultation mConsultation = new MedicalConsultation();

        try {
            mConsultation.setTypeService(type);
            mConsultation.setCpfPatient(new Cpf(cpf));
            mConsultation.setDateService(new Date(date));
            mConsultation.setReasonForService(reaso);

            FutureMedicalAppointmentDAO.add(mConsultation);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        } catch (InvalidDateException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            return;
        }

        MedicalConsultationDAO.add(mConsultation);
    }

    private void appointmentRegisterToday() {
        List<MedicalConsultation> mcList = FutureMedicalAppointmentDAO.search(today);

        Terminal.clear();
        if (mcList.size() == 0) {
            System.out.println("Não há consultas agendadas para hoje.\n");
        } else {
            System.out.println("-- Consultas para hoje --\n");

            for (MedicalConsultation mc : mcList) {
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
        int id = ReadDataFromTerminal.INT();
        MedicalConsultation mAppointment = FutureMedicalAppointmentDAO.search(id);

        if (mAppointment == null) {
            System.out.println("\nId da consulta não encontrado.\n");
            Terminal.pause();
            return;
        }

        System.out.print("\nDeseja antender está consulta?\n\n" + mAppointment
                + "\n\n:[y][n]");
        if (ReadDataFromTerminal.CHAR() == 'y') {
            FutureMedicalAppointmentDAO.delete(id);
            MedicalConsultationDAO.add(mAppointment);

            System.out.println("Paciente atendido, seu id da consulta foi atualizado.\n\n");
        } else
            System.out.println("\n\nVoltando ao menu inicial.\n\n");
        Terminal.pause();
    }

    // private void changeDate() {
    // this.today = Date.inTerminal(true, "Digite a nova data: ");
    // }
}
