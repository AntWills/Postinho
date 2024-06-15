package test;

import dbEntities.*;
import entities.patient.CPF;
import entities.patient.Patient;
import entities.patient.MedicalAppointment.*;

import java.util.List;
import java.util.ArrayList;

public class TestPatient {
    public static void main(String[] args) {
        Patient patient = Patient.inTerminal(true, "Digite os dados do patiente:\n");
    }
}
