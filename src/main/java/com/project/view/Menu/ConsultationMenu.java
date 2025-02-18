package com.project.view.Menu;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.project.model.Consultation;
import com.project.service.ConsultationService;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

public class ConsultationMenu {
    private int op;
    private Consultation consultation;

    public ConsultationMenu(Consultation medicalAppointment) {
        this.consultation = medicalAppointment;
    }

    public static void runMedicalAppointmentMenu(Consultation medicalAppointment) {
        ConsultationMenu mAppointmentMenu = new ConsultationMenu(medicalAppointment);

        do {
            mAppointmentMenu.menu();
            mAppointmentMenu.options();
        } while (mAppointmentMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Alterando Dados da Consulta Médica ##\n");
        System.out.println("Dados da consulta: ");
        System.out.println(this.consultation + "\n");

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
                ConsultationService.update(consultation);
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
        // Terminal.clear();
        // System.out.println("Digite o novo tipo de atendimento: ");
        // System.out.println("(0) : [NOT URGENT] : Blue");
        // System.out.println("(1) : [LITTLE URGENT] : Green");
        // System.out.println("(2) : [URGENT] : Yellow");
        // System.out.println("(3) : [EMERGING] : Red\n");

        // System.out.print("Digite o tipo: ");
        // this.mAppointment.setTypeService(ReadDataFromTerminal.INT());
    }

    private void updateCPF() {
        // this.mAppointment.setCpfPatient(CPF.inTerminal(true,
        // "Digite o novo CPF: "));
    }

    private void updateDate() {
        System.out.println("Digite a nova data da consulta: ");
        String dateStr = ReadDataFromTerminal.STRING();
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            return;
        }

        this.consultation.setDate(date);
    }

    private void updateReazon() {
        Terminal.clear();
        System.out.print("\nDigite a razão da consulta: ");
        this.consultation.setReasonForService(ReadDataFromTerminal.STRING());
    }

    public int getOp() {
        return op;
    }
}
