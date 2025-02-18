package com.project.view.Menu;

import com.project.entity.*;
import com.project.exception.InvalidCpfException;
import com.project.exception.InvalidDateException;
import com.project.exception.UtilCpf;
import com.project.model.*;
import com.project.service.*;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.dao.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class InitialMenu {
    private int op;
    private LocalDate today;

    // static {
    // System.setProperty("file.encoding", "UTF-8");
    // Charset.defaultCharset(); // Atualiza a configuração global
    // }

    public InitialMenu() {
        // try {
        // this.today = new Date("2007-12-03");
        this.today = LocalDate.now();
        // } catch (InvalidDateException e) {
        // e.printStackTrace();
        // }
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
                registerNewConsultation();
                break;
            case 2:
                scheduleNewConsultation();
                break;
            case 3:
                consultationRegisterToday();
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

    private void registerNewConsultation() {
        Terminal.clear();
        int status = 1;

        System.out.print("## Resgitrando Uma nova consulta ##\n");

        System.out.print("Digite o cpf do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.print("Digite o codigo do doutor: ");
        int idDoctor = ReadDataFromTerminal.INT();

        System.out.print("Motivacao da consulta (opcional): ");
        String reazon = ReadDataFromTerminal.STRING();

        ConsultationService.save(status, cpf, idDoctor, today, reazon);
        Terminal.pause();

        // Patient patient = PatientService.findById(cpf);

        // if (patient == null) {
        // Terminal.clear();
        // System.out.println("-- ERRO --\n");
        // System.out.println("O paciente nao esta cadastrado.");
        // System.out.println("Por favor, cadastra-lo e depois retorne.");
        // Terminal.pause();
        // }

    }

    private void scheduleNewConsultation() {
        Patient patient = null;
        Doctor doctor = null;
        LocalDate date = null;

        System.out.println("Tipo de status: ");
        System.out.println("(0) : Agendado");
        System.out.println("(1) : Realizado");
        System.out.println("(2) : Cancelado");
        System.out.println("(3) : Nao realizado\n");

        System.out.print("Digite o tipo: ");
        int status = ReadDataFromTerminal.INT();

        System.out.println("Digite o CPF do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.println("Digite o codigo do doutor de plantao: ");
        int idDoctor = ReadDataFromTerminal.INT();

        System.out.println("Digite a data: ");
        String dateString = ReadDataFromTerminal.STRING();

        System.out.print("\nDigite a razão da consulta (Opcional): ");
        String reaso = ReadDataFromTerminal.STRING();

        // Consultation mConsultation = new Consultation();

        try {
            UtilCpf.validator(cpf);
            patient = PatientService.findById(cpf);
            doctor = DoctorService.findById(idDoctor);
            date = LocalDate.parse(dateString);

        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            return;
        } catch (DateTimeParseException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            return;
        }

        if (patient == null) {
            System.out.println("## Erro ##");
            System.out.println("O paciente nao esta cadastrado.");
            System.out.println("Favor cadastrar.");
        }

        if (doctor == null) {
            System.out.println("## Erro ##");
            System.out.println("O doutor nao esta cadastrado.");
            System.out.println("Favor cadastrar.");
        }

        ConsultationService.save(status, patient, doctor, date, reaso);
    }

    private void consultationRegisterToday() {
        // List<Consultation> mcList = FutureMedicalAppointmentDAO.search(today);

        // Terminal.clear();
        // if (mcList.size() == 0) {
        // System.out.println("Não há consultas agendadas para hoje.\n");
        // } else {
        // System.out.println("-- Consultas para hoje --\n");

        // for (Consultation mc : mcList) {
        // System.out.println(mc + "\n");
        // }
        // System.out.println("Um total de " + mcList.size()
        // + " agendadas para hoje.\n\n");
        // }

        // Terminal.pause();
    }

    private void assistPatientToday() {
        Terminal.clear();
        System.out.println("-- Atendendo consulta marcada para " + today + " --\n");
        System.out.println("Digite o CPF do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();
        Patient patient = new Patient();

        try {
            patient.setCpfId(cpf);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");
        }

        Consultation consultation = ConsultationService.findByPatientAndDate(patient, today);

        if (consultation == null) {
            System.out.println("\nId da consulta não encontrado.\n");
            Terminal.pause();
            return;
        }

        System.out.print("\nDeseja antender está consulta?\n\n"
                + "\n\n:[y][n]");
        if (ReadDataFromTerminal.CHAR() == 'y') {
            consultation.setStatus(1);
            ConsultationService.update(consultation);

            System.out.println("Paciente atendido, seu status da consulta foi atualizado.\n");
        }

        System.out.println("\n\nVoltando ao menu inicial.\n\n");
        Terminal.pause();
    }

    // private void changeDate() {
    // this.today = Date.inTerminal(true, "Digite a nova data: ");
    // }
}
