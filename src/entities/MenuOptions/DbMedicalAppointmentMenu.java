package entities.MenuOptions;

import dbEntities.MedicalAppointmentDAO;
import entities.patient.MedicalAppointment.MedicalAppointment;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class DbMedicalAppointmentMenu {
    private int op;

    public static void runDbMedical() {
        DbMedicalAppointmentMenu dbMenu = new DbMedicalAppointmentMenu();

        do {
            dbMenu.menu();
            dbMenu.options();
        } while (dbMenu.getOp() != 0);
    }

    public void menu() {
        Terminal.clear();
        System.out.println("# Menu das Consultas #\n");

        System.out.println("[1] : Buscar consulta por id.");
        System.out.println("[2] : Buscar consultas por CPF.");
        System.out.println("[3] : Buscar consulta por data.");
        System.out.println("[4] : Buscar consulta futura por id.");
        System.out.println("[5] : Buscar consultas futura por CPF.");
        System.out.println("[6] : Buscar consulta futura por data.");
        System.out.println("[0] : Voltar.");

        System.out.println("Digite uma das opções: ");
        this.op = ReadData.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                searchMedicalAppointmentId();
                break;
            default:
                break;
        }
    }

    public int getOp() {
        return op;
    }

    private void searchMedicalAppointmentId() {
        Terminal.clear();
        System.out.println("Digite o id da consulta: ");
        MedicalAppointment mAppointment = MedicalAppointmentDAO.seek(ReadData.INT());
        Terminal.clear();
        if (mAppointment == null) {
            System.out.println("Consulta não encontrada.\n");
        } else {
            System.out.println("Dados da consulta:\n\n" + mAppointment + "\n");
        }

        Terminal.pause();
    }
}
