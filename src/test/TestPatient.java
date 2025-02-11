package test;

import entities.patient.Patient;

public class TestPatient {
    public static void main(String[] args) {
        Patient patient = Patient.inTerminal(true, "Digite os dados do patiente:\n");
    }
}
