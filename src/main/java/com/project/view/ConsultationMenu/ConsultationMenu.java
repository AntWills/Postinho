package com.project.view.ConsultationMenu;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

import com.project.exception.InvalidCpfException;
import com.project.model.Consultation;
import com.project.model.Patient;
import com.project.service.ConsultationService;
import com.project.service.PatientService;
import com.project.util.EntityUtil;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

public class ConsultationMenu {
    private int op;
    private Consultation consultation;

    public ConsultationMenu(Consultation medicalAppointment) {
        this.consultation = medicalAppointment;
    }

    public static void runMedicalConsultation(Consultation medicalAppointment) {
        ConsultationMenu mAppointmentMenu = new ConsultationMenu(medicalAppointment);

        do {
            mAppointmentMenu.menu();
            mAppointmentMenu.options();
        } while (mAppointmentMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Alterando Dados da Consulta Médica ##\n");
        System.out.println("Dados da consulta:\n");
        EntityUtil.printInTerminal(consultation);
        System.out.println("");

        System.out.println("[1] : Status da consulta.");
        System.out.println("[2] : O cpf do paciente.");
        System.out.println("[2] : O código do doutor.");
        System.out.println("[4] : A data.");
        System.out.println("[5] : A razão da consulta.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções para alterar: ");
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 0:
                try {
                    ConsultationService.update(consultation);
                } catch (SQLException e) {
                    Terminal.pause();
                    System.out.println("## ERRO ##");
                    System.out.println("- Não foi possivel atualizar os dados.\n");
                    Terminal.pause();
                }
                break;
            case 1:
                updateType();
                break;
            case 2:
                updateCPF();
                break;
            case 4:
                updateDate();
                break;
            case 5:
                updateReazon();
                break;
        }
    }

    private void updateType() {
        Terminal.clear();
        System.out.println("-- Atualizando status da consulta --\n");

        System.out.println("(0) : [Agendado]");
        System.out.println("(1) : [Atendido]");
        System.out.println("(2) : [Cancelado]");
        System.out.println("(3) : [Não realizado]\n");

        System.out.print("Digite o novo status: ");
        this.consultation.setStatus(ReadDataFromTerminal.INT());
    }

    private void updateCPF() {
        Terminal.clear();
        System.out.println("-- Atualizando o cpf do paciente --\n");

        System.out.print("Digite o novo cpf: ");
        String cpf = ReadDataFromTerminal.STRING();

        Patient patient = new Patient();

        Terminal.clear();
        try {
            patient.setCpfId(cpf);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        }
        Patient patientAux = null;
        try {
            patientAux = PatientService.findById(cpf);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("- Ouvi um erro ao buscar o paciente.");
            Terminal.pause();
            return;
        }

        if (patientAux == null) {
            System.out.println("O cpf " + cpf + " não corresponde a nenhuma paciente.\n");
            Terminal.pause();
            return;
        }
        this.consultation.setPatient(cpf);
    }

    private void updateDate() {
        Terminal.clear();
        System.out.println("-- Atualizando a data da consulta --\n");

        System.out.println("Digite a nova data: ");
        String dateStr = ReadDataFromTerminal.STRING();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            System.out.println("## Erro ##");
            System.out.println("- Data digitada de forma errada.\n");
            Terminal.pause();
            return;
        }

        this.consultation.setDate(date);
    }

    private void updateReazon() {
        Terminal.clear();
        System.out.println("-- Atualizando a razão --\n");

        System.out.print("Digite a razão da consulta: ");
        this.consultation.setReasonForService(ReadDataFromTerminal.STRING());
    }

    public int getOp() {
        return op;
    }
}
