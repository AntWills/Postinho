package com.project.view.Menu;

import com.project.model.MedicalConsultation;
import com.project.model.Patient;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.dao.*;
import java.util.List;

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
        System.out.println("[3] : Atualizar ultimas consulta.");
        System.out.println("[4] : Remarcar consultas futuras.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                printAppointmentPatient();
                break;
            case 2:
                printFutureAppointmentPatient();
                break;
            case 3:
                updateMedicalAppointment();
                break;
            case 4:
                updateFutureMedicalAppointment();
                break;
            default:
                break;
        }
    }

    private void printAppointmentPatient() {
        Terminal.clear();
        List<MedicalConsultation> list = this.patient.getConsultations();

        if (list.size() == 0) {
            System.out.println("Não há consultas realizadas para este paciente.\n");
        } else {
            System.out.println("Consultas realizadas pelo paciente: " + this.patient.getName() + "\n");

            for (MedicalConsultation mAppointment : list) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void printFutureAppointmentPatient() {
        Terminal.clear();
        List<MedicalConsultation> list = FutureMedicalAppointmentDAO.search(this.patient.geCpft());

        if (list.size() == 0) {
            System.out.println("Não há consultas agendadas para este paciente.\n");
        } else {
            System.out.println("Consultas marcadas pelo paciente: " + this.patient.getName() + "\n");

            for (MedicalConsultation mAppointment : list) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void updateMedicalAppointment() {
        // Terminal.clear();
        // System.out.print("Digite o id da consulta: ");
        // int id = ReadDataFromTerminal.INT();

        // MedicalConsultation mAppointment = patient.getMedicalAppointmentID(id);

        // if (mAppointment == null) {
        // Terminal.clear();
        // System.out.println("\nConsulta com o id igual a " + id + " não foi
        // encontrada.");
        // System.out.println("Voltando ao menu.\n");
        // Terminal.pause();
        // return;
        // }

        // MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        // MedicalConsultationDAO.updade(id, mAppointment);
    }

    private void updateFutureMedicalAppointment() {
        Terminal.clear();
        System.out.print("Digite o id da consulta: ");
        int id = ReadDataFromTerminal.INT();

        MedicalConsultation mAppointment = FutureMedicalAppointmentDAO.search(id, patient.geCpft());

        if (mAppointment == null) {
            Terminal.clear();
            System.out.println("\nConsulta com o id igual a " + id + " não foi encontrada.");
            System.out.println("Voltando ao menu.\n");
            Terminal.pause();
            return;
        }

        MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        FutureMedicalAppointmentDAO.updade(id, mAppointment);
    }

    public int getOp() {
        return op;
    }
}
