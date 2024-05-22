package entities;

import entities.terminal.ReadData;
import entities.MedicalCare.MedicalCare;
import java.util.List;
import java.util.ArrayList;
// import java.util.LinkedList;

public class Patient {
    int CPF;
    String name;
    List<MedicalCare> consultateCarried;

    public Patient() {
        this.CPF = 0;
        this.name = "";
        this.consultateCarried = new ArrayList<>();
    }

    public Patient(int CPF, String name) {
        this.CPF = CPF;
        this.name = name;
    }

    public Patient(int CPF, String name, List<MedicalCare> cultateList) {
        this.CPF = CPF;
        this.name = name;
        this.consultateCarried = cultateList;
    }

    public void in() {
        System.err.println("\nDigite os seguintes dados do paciente:\n");

        System.err.println("Digite o CPF: ");
        this.CPF = ReadData.INT();
        System.err.println("Digite o nome: ");
        this.name = ReadData.STRING();
    }

    @Override
    public String toString() {
        return "CPF: " + CPF + " : Nome: " + name
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

    public int getId() {
        return CPF;
    }

    public void setId(int CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
