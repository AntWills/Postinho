package com.project.view.Menu;

import com.project.entity.Cpf;
import com.project.entity.Date;
import com.project.exception.InvalidDateException;
import com.project.model.MedicalConsultation;
import com.project.service.MedicalConsultationService;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;

import java.util.List;

import com.project.dao.*;

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
        this.op = ReadDataFromTerminal.INT();
    }

    public void options() {
        switch (this.op) {
            case 1:
                searchMedicalAppointmentId();
                break;
            case 2:
                searchMedicalConsultationCpf();
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
        int id = ReadDataFromTerminal.INT();

        MedicalConsultation mAppointment = MedicalConsultationService.findById(id);

        Terminal.clear();
        if (mAppointment == null) {
            System.out.println("Consulta não encontrada.\n");
        } else {
            System.out.println("Dados da consulta:\n\n" + mAppointment + "\n");

            System.out.println("Dejeja editar está consulta?[y][n]");
            if (ReadDataFromTerminal.CHAR() == 'y')
                MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        }
    }

    private void searchMedicalConsultationCpf() {
        // CPF cpf = CPF.inTerminal(true, "Digite o CPF: ");

        Cpf cpf = null;
        List<MedicalConsultation> mAppointmentList = MedicalConsultationService.findByCpf(cpf);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com CPF: " + cpf + " --\n");
            for (MedicalConsultation mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchMedicalAppointmentDate() {
        System.out.println("Digite a data: ");
        String dateStr = ReadDataFromTerminal.STRING();
        Date date = new Date();

        try {
            date.setData(dateStr);
        } catch (InvalidDateException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            return;
        }

        List<MedicalConsultation> mAppointmentList = MedicalConsultationService.findByDate(date);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com a data: " + date + " --\n");
            for (MedicalConsultation mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchFutureMedicalAppointmentId() {
        Terminal.clear();
        System.out.println("Digite o id da consulta: ");
        MedicalConsultation mAppointment = FutureMedicalAppointmentDAO.search(ReadDataFromTerminal.INT());

        Terminal.clear();
        if (mAppointment == null) {
            System.out.println("Consulta não encontrada.\n");
        } else {
            System.out.println("Dados da consulta:\n\n" + mAppointment + "\n");

            System.out.println("Dejeja editar está consulta?[y][n]");
            if (ReadDataFromTerminal.CHAR() == 'y')
                MedicalAppointmentMenu.runMedicalAppointmentMenu(mAppointment);
        }
    }

    private void searchFutureMedicalAppointmentCpf() {
        // CPF cpf = CPF.inTerminal(true, "Digite o CPF: ");
        Cpf cpf = null;
        List<MedicalConsultation> mAppointmentList = FutureMedicalAppointmentDAO.search(cpf);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com CPF: " + cpf + " --\n");
            for (MedicalConsultation mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }

    private void searchFutureMedicalAppointmentDate() {
        System.out.println("Digite a data: ");
        String dateStr = ReadDataFromTerminal.STRING();
        Date date = new Date();

        try {
            date.setData(dateStr);
        } catch (InvalidDateException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            return;
        }

        List<MedicalConsultation> mAppointmentList = FutureMedicalAppointmentDAO.search(date);

        Terminal.clear();
        if (mAppointmentList == null) {
            System.out.println("Nenhuma consulta foi encontrada.\n");
        } else {
            System.out.println("-- Consultas com a data: " + date + " --\n");
            for (MedicalConsultation mAppointment : mAppointmentList) {
                System.out.println(mAppointment + "\n");
            }
        }
        Terminal.pause();
    }
}
