package entities.MenuOptions;

import java.util.List;

import dbEntities.FutureMedicalAppointmentDAO;
import dbEntities.MedicalAppointmentDAO;
import entities.patient.CPF;
import entities.patient.MedicalAppointment.Date;
import entities.patient.MedicalAppointment.MedicalAppointment;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

public class DbMedicalAppointmentMenu {
    private int op;

    public static void runDbMedicalAppointmentMenu() {
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
        System.out.println("[0] : Voltar.\n");

        System.out.print("Digite uma das opções: ");
        this.op = ReadData.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                searchMedicalAppointmentId();
                break;
            case 2:
                searchMedicalAppointmentCpf();
                break;
            case 3:
                searchMedicalAppointmentDate();
                break;
            case 4:
                searchFutureMedicalAppointmentId();
                break;
            case 5:
                searchFutureMedicalAppointmentCpf();
                break;
            case 6:
                searchFutureMedicalAppointmentDate();
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

    private void searchMedicalAppointmentId() {
        Terminal.clear();
        System.out.println("Digite o id da consulta: ");
        MedicalAppointment mAppointment = MedicalAppointmentDAO.search(ReadData.INT());

        Terminal.clear();
        if (mAppointment == null) {
            System.out.println("Consulta não encontrada.\n");
        } else {
            System.out.println("Dados da consulta:\n\n" + mAppointment + "\n");

            System.out.println("Dejeja editar está consulta?[y][n]");
            if (ReadData.CHAR() == 'y')
                MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        }
    }

    private void searchMedicalAppointmentCpf() {
        CPF cpf = CPF.inTerminal(true, "Digite o CPF: ");
        List<MedicalAppointment> mAppointmentList = MedicalAppointmentDAO.search(cpf);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com CPF: " + cpf + " --\n");
            for (MedicalAppointment mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchMedicalAppointmentDate() {
        Date date = Date.inTerminal(true, "Digite a data:");
        List<MedicalAppointment> mAppointmentList = MedicalAppointmentDAO.search(date);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com a data: " + date + " --\n");
            for (MedicalAppointment mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchFutureMedicalAppointmentId() {
        Terminal.clear();
        System.out.println("Digite o id da consulta: ");
        MedicalAppointment mAppointment = FutureMedicalAppointmentDAO.search(ReadData.INT());

        Terminal.clear();
        if (mAppointment == null) {
            System.out.println("Consulta não encontrada.\n");
        } else {
            System.out.println("Dados da consulta:\n\n" + mAppointment + "\n");

            System.out.println("Dejeja editar está consulta?[y][n]");
            if (ReadData.CHAR() == 'y')
                MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        }
    }

    private void searchFutureMedicalAppointmentCpf() {
        CPF cpf = CPF.inTerminal(true, "Digite o CPF: ");
        List<MedicalAppointment> mAppointmentList = FutureMedicalAppointmentDAO.search(cpf);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com CPF: " + cpf + " --\n");
            for (MedicalAppointment mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchFutureMedicalAppointmentDate() {
        Date date = Date.inTerminal(true, "Digite a data:");
        List<MedicalAppointment> mAppointmentList = FutureMedicalAppointmentDAO.search(date);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com a data: " + date + " --\n");
            for (MedicalAppointment mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }
}
