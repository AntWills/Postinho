package com.project.view.PatientMenu;

import com.project.model.Consultation;
import com.project.model.Patient;
import com.project.util.EntityUtil;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.view.ConsultationMenu.ConsultationMenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        System.out.println("-- Paciente : " + patient.getName() + " --\n");

        System.out.println("Dados do paciente:\n");
        EntityUtil.printInTerminal(patient);

        System.out.println("\n[1] : Imprimir ultimas consultas.");
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
                printConsultatioinPatient();
                break;
            case 2:
                printFutureConsultationPatient();
                break;
            case 3:
                updateConsultation();
                break;
            case 4:
                updateFutureConsultation();
                break;
            default:
                break;
        }
    }

    private void printConsultatioinPatient() {
        Terminal.clear();
        List<Consultation> list = this.patient.getConsultations();

        if (list.size() == 0) {
            System.out.println("Não há consultas realizadas para este paciente.\n");
        } else {
            System.out.println("Consultas realizadas pelo paciente: " + this.patient.getName() + "\n");

            for (Consultation mAppointment : list) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void printFutureConsultationPatient() {
        Terminal.clear();
        LocalDate today = LocalDate.now();
        List<Consultation> list = this.patient
                .getConsultations().stream()
                .filter(consultation -> consultation.getDate().isAfter(today))
                .collect(Collectors.toList());

        if (list.size() == 0) {
            System.out.println("Não há consultas agendadas para este paciente.\n");
            Terminal.pause();
            return;
        }

        System.out.println("Consultas marcadas pelo paciente: " +
                this.patient.getName() + "\n");

        for (Consultation mAppointment : list) {
            System.out.println(mAppointment + "\n");
        }

        Terminal.pause();
    }

    private void updateConsultation() {
        Terminal.clear();
        System.out.print("Digite o id da consulta: ");
        int id = ReadDataFromTerminal.INT();

        Optional<Consultation> consultationOp = this.patient
                .getConsultations().stream()
                .filter(consultation -> consultation.getId() == id)
                .findFirst();

        if (!consultationOp.isPresent()) {
            System.out.println("\nConsulta com o id igual a " + id + " não foi encontrada.\n");
            Terminal.pause();
            return;
        }

        Consultation consultation = consultationOp.get();

        ConsultationMenu.runMedicalConsultation(consultation);

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

    private void updateFutureConsultation() {
        // Terminal.clear();
        // System.out.print("Digite o id da consulta: ");
        // int id = ReadDataFromTerminal.INT();

        // Consultation mAppointment = FutureMedicalAppointmentDAO.search(id,
        // patient.geCpft());

        // if (mAppointment == null) {
        // Terminal.clear();
        // System.out.println("\nConsulta com o id igual a " + id + " não foi
        // encontrada.");
        // System.out.println("Voltando ao menu.\n");
        // Terminal.pause();
        // return;
        // }

        // MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        // FutureMedicalAppointmentDAO.updade(id, mAppointment);
    }

    public int getOp() {
        return op;
    }
}
