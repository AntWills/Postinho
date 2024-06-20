package entities.MenuOptions;

import entities.patient.CPF;
import entities.patient.MedicalAppointment.Date;
import entities.patient.MedicalAppointment.MedicalAppointment;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class MedicalAppointmentMenu {
    private int op;
    private MedicalAppointment mAppointment;

    public MedicalAppointmentMenu(MedicalAppointment medicalAppointment) {
        this.mAppointment = medicalAppointment;
    }

    public static void runMedicalAppointmentMenu(MedicalAppointment medicalAppointment) {
        MedicalAppointmentMenu mAppointmentMenu = new MedicalAppointmentMenu(medicalAppointment);

        do {
            mAppointmentMenu.menu();
            mAppointmentMenu.options();
        } while (mAppointmentMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("## Alterando Dados da Consulta Médica ##\n");
        System.out.println("Dados da consulta: ");
        System.out.println(this.mAppointment + "\n");

        System.out.println("[1] : O tipo de consulta.");
        System.out.println("[2] : O CPF.");
        System.out.println("[3] : A data.");
        System.out.println("[4] : A razão da consulta.");
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções para alterar: ");
        this.op = ReadData.INT();
    }

    public void options() {
        switch (this.op) {
            case 0:
                break;
            case 1:
                updateType();
                break;
            case 2:
                updateCPF();
                break;
            case 3:
                updateDate();
                break;
            case 4:
                updateReazon();
                break;
        }
    }

    private void updateType() {
        Terminal.clear();
        System.out.println("Digite o novo tipo de atendimento: ");
        System.out.println("(0) : [NOT URGENT] : Blue");
        System.out.println("(1) : [LITTLE URGENT] : Green");
        System.out.println("(2) : [URGENT] : Yellow");
        System.out.println("(3) : [EMERGING] : Red\n");

        System.out.print("Digite o tipo: ");
        this.mAppointment.setTypeService(ReadData.INT());
    }

    private void updateCPF() {
        this.mAppointment.setCpfPatient(CPF.inTerminal(true,
                "Digite o novo CPF: "));
    }

    private void updateDate() {
        this.mAppointment.setDateService(Date.inTerminal(true,
                "Digite a nova data da consulta: "));
    }

    private void updateReazon() {
        Terminal.clear();
        System.out.print("\nDigite a razão da consulta: ");
        this.mAppointment.setReasonForService(ReadData.STRING());
    }

    public int getOp() {
        return op;
    }
}
