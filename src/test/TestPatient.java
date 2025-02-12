package test;

import java.com.project.entity.patient.Patient;

public class TestPatient {
    public static void main(String[] args) {
        Patient patient = Patient.inTerminal(true, "Digite os dados do patiente:\n");
    }
}
