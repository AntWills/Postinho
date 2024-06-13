package entities.patient;

import entities.patient.MedicalCare.MedicalCare;
import entities.terminal.ReadData;

import java.util.List;
import java.util.ArrayList;

public class Patient {
    CPF cpf;
    String name;
    List<MedicalCare> consultateCarried;

    public Patient() {
        this.cpf = new CPF();
        this.name = "";
        this.consultateCarried = new ArrayList<>();
    }

    public Patient(CPF cpf, String name) {
        this.cpf = cpf;
        this.name = name;
        this.consultateCarried = new ArrayList<>();
    }

    public Patient(CPF cpf, String name, List<MedicalCare> cultateList) {
        this.cpf = cpf;
        this.name = name;
        this.consultateCarried = cultateList;
    }

    public void in() {
        System.err.println("Digite os seguintes dados do paciente:\n");

        this.cpf.in();
        System.err.println("Digite o nome: ");
        this.name = ReadData.STRING();
    }

    @Override
    public String toString() {
        return "cpf: " + cpf + " - Nome: " + name
                + "\nPossui um total de " + consultateCarried.size()
                + " consultas realizadas.\n";
    }

    public void printAllCare() {
        if (consultateCarried.size() == 0) {
            System.err.println("O paciente " + name + " ainda não fez consultas.");
            return;
        }

        System.err.println("\nTotas as consultas feitas por " + name + ":\n");

        for (MedicalCare care : consultateCarried) {
            System.err.println(care + "\n");
        }

    }

    public CPF getId() {
        return cpf;
    }

    public void setId(CPF cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
