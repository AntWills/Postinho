package com.project.view.ConsultationMenu;

import com.project.model.Consultation;
import com.project.model.Patient;
import com.project.service.ConsultationService;
import com.project.util.EntityUtil;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class DbConsultationMenu {
    private int op;

    public static void runDbMedicalAppointmentMenu() {

        DbConsultationMenu dbMenu = new DbConsultationMenu();

        do {
            dbMenu.menu();
            dbMenu.options();
        } while (dbMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("# Menu das Consultas #\n");

        System.out.println("[1] : Buscar consulta por código.");
        System.out.println("[2] : Buscar consultas por CPF.");
        System.out.println("[3] : Buscar consulta por data.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                searchConsultationId();
                break;
            case 2:
                searchConsultationCpf();
                break;
            case 3:
                searchConsultationDate();
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    public int getOp() {
        return op;
    }

    private void searchConsultationId() {
        Terminal.clear();
        System.out.println("-- Buscando Consulta por código --\n");
        System.out.print("Digite o código da consulta: ");
        int id = ReadDataFromTerminal.INT();

        Consultation consultation = null;
        try {
            consultation = ConsultationService.findById(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Terminal.pause();
            return;
        }

        Terminal.clear();
        if (consultation == null) {
            System.out.println("Consulta não encontrada.\n");
            Terminal.clear();
            return;
        }
        ConsultationMenu.runMedicalConsultation(consultation);
    }

    private void searchConsultationCpf() {
        Terminal.clear();
        System.out.println("-- Buscando Consultas por cpf do paciente --\n");

        System.out.print("Digite o cpf: ");
        String cpf = ReadDataFromTerminal.STRING();
        Patient patient = new Patient();
        // patient.setCpfId(cpf);

        Terminal.clear();
        try {
            patient.setCpfId(cpf);
        } catch (Exception e) {
            System.err.println("## Erro ##");
            System.err.println("- Cpf digitado de forma incorreta.\n");
            Terminal.pause();
            return;
        }

        List<Consultation> consultations = null;
        try {
            consultations = ConsultationService.findByPatient(patient);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("");
            Terminal.pause();
            return;
        }

        if (consultations == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
            Terminal.pause();
            return;
        }
        System.out.println("-- Consultas com CPF: " + cpf + " --\n");
        for (Consultation consultation : consultations) {
            EntityUtil.printInTerminal(consultation);
            System.out.println("-----------------------------");
        }

        Terminal.pause();
    }

    private void searchConsultationDate() {
        Terminal.clear();
        System.out.println("-- Buscando Consultas por data --\n");

        System.out.print("Digite a data: ");
        String dateStr = ReadDataFromTerminal.STRING();
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            System.out.println("## Erro ##");
            System.out.println("- Data digitada de forma incorreta.");
            System.out.println(e.getMessage());
            return;
        }

        List<Consultation> consultationList = null;
        try {
            consultationList = ConsultationService.findByDate(date);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Terminal.pause();
            return;
        }

        Terminal.clear();
        if (consultationList.size() == 0) {
            System.out.println("Nenhuma consulta foi encontrada para" + date + ".\n");
        } else {
            System.out.println("-- Consultas com a data: " + date + " --\n");
            for (Consultation consultation : consultationList) {
                EntityUtil.printInTerminal(consultation);
                System.out.println("------------------------------\n");
            }
        }
        Terminal.pause();
    }

}
