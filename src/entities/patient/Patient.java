package entities.patient;

import entities.patient.MedicalAppointment.*;
import entities.terminal.ReadData;
import entities.terminal.Terminal;

import java.util.List;
import java.util.ArrayList;

public class Patient {
    CPF cpf;
    String name;
    List<MedicalAppointment> medicalAppointmentsList;

    public Patient() {
        this.cpf = new CPF();
        this.name = "";
        this.medicalAppointmentsList = new ArrayList<>();
    }

    public Patient(CPF cpf, String name) {
        this.cpf = cpf;
        this.name = name;
        this.medicalAppointmentsList = new ArrayList<>();
    }

    public Patient(CPF cpf, String name, List<MedicalAppointment> cultateList) {
        this.cpf = cpf;
        this.name = name;
        this.medicalAppointmentsList = cultateList;
    }

    private static void inTerminal(Patient patient) {
        patient.setCpfId(CPF.inTerminal("Digite o CPF: "));
        System.err.print("Digite o nome: ");
        patient.setName(ReadData.STRING());
    }

    public static Patient inTerminal(String msg) {
        Patient patient = new Patient();
        System.out.println(msg);
        Patient.inTerminal(patient);
        return patient;
    }

    public static Patient inTerminal(boolean activateLoop, String msg) {
        Patient patient = new Patient();

        char confirmation = '0';
        do {
            Terminal.clear();
            System.out.println(msg);
            Patient.inTerminal(patient);
            Terminal.clear();

            System.out.print("O dados do patiente::\n\n" + patient.toString()
                    + "\n\nEstão corretos?[y][n]");
            confirmation = ReadData.CHAR();
        } while (confirmation != 'y');

        return patient;
    }

    @Override
    public String toString() {
        return "cpf: " + cpf + " - Nome: " + name
                + "\nPossui um total de " + medicalAppointmentsList.size()
                + " consultas realizadas.";
    }

    public void printAllAppointment() {
        if (medicalAppointmentsList.size() == 0) {
            System.err.println("O paciente " + name + " ainda não fez consultas.");
            return;
        }

        System.err.println("\nTotas as consultas feitas por " + name + ":\n");

        for (MedicalAppointment care : medicalAppointmentsList) {
            System.err.println(care + "\n");
        }

    }

    public CPF geCpftId() {
        return cpf;
    }

    public void setCpfId(CPF cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MedicalAppointment> getMedicalAppointmentsList() {
        return medicalAppointmentsList;
    }

    public MedicalAppointment getMedicalAppointmentID(int id) {
        List<MedicalAppointment> list = this.medicalAppointmentsList;

        for (MedicalAppointment mAppointment : list) {
            if (mAppointment.getID() == id)
                return mAppointment;
        }
        return null;
    }

}
