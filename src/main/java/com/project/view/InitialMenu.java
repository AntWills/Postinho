package com.project.view;

import com.project.exception.InvalidCpfException;
import com.project.model.*;
import com.project.service.*;
import com.project.util.EntityUtil;
import com.project.util.ReadDataFromTerminal;
import com.project.util.Terminal;
import com.project.util.UtilCpf;
import com.project.view.ConsultationMenu.DbConsultationMenu;
import com.project.view.MenuDoctor.DbDoctorMenu;
import com.project.view.PatientMenu.DbPatientMenu;

import java.sql.SQLException;
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
        System.out.println("[2] : Agendar consulta.");
        System.out.println("[3] : Consultas agendados para hoje.");
        System.out.println("[4] : Atender consulta de hoje.");
        System.out.println("[5] : Acessar banco de consultas(+).");
        System.out.println("[6] : Acessar banco de pacientes(+).");
        System.out.println("[7] : Acessar banco de doutores(+).");
        System.out.println("[8] : Alterar data do dia atual.");
        System.out.println("[0] : Encerrar programa.");

        System.out.print("\nDigite uma das opções: ");
        op = ReadDataFromTerminal.INT();
    }

    public int getOpInt() {
        return op;
    }

    public void options() {
        switch (op) {
            case 0:
                break;
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
                DbConsultationMenu.runDbMedicalAppointmentMenu();
                break;
            case 6:
                DbPatientMenu.runDbPatientMenu();
                break;
            case 7:
                DbDoctorMenu.runDoctorMenu();
                break;
            default:
                break;
        }
    }

    private void registerNewConsultation() {
        Terminal.clear();
        int status = 1;

        System.out.println("## Resgitrando Uma nova consulta ##\n");

        System.out.print("Digite o cpf do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.print("Digite o codigo do doutor: ");
        int idDoctor = ReadDataFromTerminal.INT();

        System.out.print("Motivacao da consulta (opcional): ");
        String reazon = ReadDataFromTerminal.STRING();

        Terminal.clear();

        Patient patient;
        try {
            patient = PatientService.findById(cpf);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao buscar os dados do paciente.\n");
            Terminal.pause();
            return;
        }

        if (patient == null) {
            System.out.println("Paciente não cadastrado.");
            System.out.println("Por favor, cadastrar o paciente.\n");
            Terminal.pause();
            return;
        }

        Doctor doctor;
        try {
            doctor = DoctorService.findById(idDoctor);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao buscar os dados do doutor.\n");
            Terminal.pause();
            return;
        }

        if (doctor == null) {
            System.out.println("Doutor não cadastrado.");
            System.out.println("Por favor, cadastrar o doutor.\n");
            Terminal.pause();
            return;
        }

        try {
            ConsultationService.save(status, patient, doctor, today, reazon);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("- Ouvi um erro ao salvar os dados da consulta.\n");
            System.err.println(": " + e.getMessage());
        }
        Terminal.pause();

    }

    private void scheduleNewConsultation() {
        LocalDate date = null;
        int status = 0;
        Terminal.clear();
        System.out.println("-- Agendando Consulta --\n");

        System.out.print("Digite o CPF do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();

        System.out.print("Digite o código do doutor de plantão: ");
        int idDoctor = ReadDataFromTerminal.INT();

        System.out.print("Digite a data: ");
        String dateString = ReadDataFromTerminal.STRING();

        System.out.print("\nDigite a razão da consulta (Opcional): ");
        String reazon = ReadDataFromTerminal.STRING();

        // Consultation mConsultation = new Consultation();

        Terminal.clear();

        Patient patient;
        try {
            patient = PatientService.findById(cpf);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("- Ouvi um erro ao buscar os dados do paciente.\n");
            System.err.println(":: " + e.getMessage() + "\n");
            Terminal.pause();
            return;
        }

        if (patient == null) {
            System.out.println("Paciente não cadastrado.");
            System.out.println("Por favor, cadastrar o paciente.\n");
            Terminal.pause();
            return;
        }

        Doctor doctor;
        try {
            doctor = DoctorService.findById(idDoctor);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao buscar os dados do doutor.\n");
            Terminal.pause();
            return;
        }

        if (doctor == null) {
            System.out.println("Doutor não cadastrado.");
            System.out.println("Por favor, cadastrar o doutor.\n");
            Terminal.pause();
            return;
        }

        try {
            UtilCpf.validator(cpf);
            date = LocalDate.parse(dateString);

        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");

            Terminal.pause();
            return;
        } catch (DateTimeParseException e) {
            System.out.println("## Erro ##");
            System.out.println(e.getMessage());

            Terminal.pause();
            return;
        }

        if (date.isBefore(today)) {
            System.out.println("\nA data marcada não pode anteior a data atual.\n");
            Terminal.pause();
            return;
        }

        try {
            ConsultationService.save(status, patient, doctor, date, reazon);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao salvar os dados da consulta.\n");
        }
        Terminal.pause();
    }

    private void consultationRegisterToday() {
        List<Consultation> consultationList = null;
        Terminal.clear();
        try {
            consultationList = ConsultationService.findByDate(today);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao buscar os dados as consultas para hoje.\n");

            Terminal.pause();
            return;
        }

        if (consultationList.size() == 0) {
            System.out.println("Não há consultas agendadas para hoje.\n");
        } else {
            System.out.println("-- Consultas para hoje --\n");

            for (Consultation consultation : consultationList) {
                EntityUtil.printInTerminal(consultation);
                System.out.println("---------------------------------------");
            }
            System.out.println("\nUm total de " + consultationList.size()
                    + " agendadas para hoje.\n\n");
        }

        Terminal.pause();
    }

    private void assistPatientToday() {
        Terminal.clear();
        System.out.println("-- Atendendo consulta marcada para " + today + " --\n");
        System.out.print("Digite o CPF do paciente: ");
        String cpf = ReadDataFromTerminal.STRING();
        Patient patient = new Patient();

        try {
            patient.setCpfId(cpf);
        } catch (InvalidCpfException e) {
            System.out.println("## Erro ##");
            System.out.println("- Cpf digitado de forma incorreta.");
            System.out.println("- O cpf é invalido.");
        }

        Consultation consultation = null;
        try {
            consultation = ConsultationService.findByPatientAndDate(patient, today);
        } catch (SQLException e) {
            System.err.println("Ouvi um erro ao buscar a consulta.\n");
        }

        if (consultation == null) {
            System.out.println("\nCódigo da consulta não encontrado.\n");
            Terminal.pause();
            return;
        }

        System.out.print("\nDeseja antender está consulta? [y][n]\n");
        EntityUtil.printInTerminal(consultation);
        System.out.println("\n::");

        if (ReadDataFromTerminal.CHAR() != 'y') {
            return;
        }

        consultation.setStatus(1);
        try {
            ConsultationService.update(consultation);
        } catch (SQLException e) {
            System.err.println("## ERRO ##");
            System.err.println("Ouvi um erro ao buscar a consulta.\n");
            Terminal.pause();
            return;
        }

        System.out.println("Paciente atendido, seu status da consulta foi atualizado.\n");

        System.out.println("\n\nVoltando ao menu inicial.\n\n");
        Terminal.pause();
    }

    // private void changeDate() {
    // this.today = Date.inTerminal(true, "Digite a nova data: ");
    // }
}
