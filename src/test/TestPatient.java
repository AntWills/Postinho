package test;

import entities.CPF;
import entities.Patient;
import entities.MedicalCare.*;
import java.util.List;
import java.util.ArrayList;

public class TestPatient {
    public static void main(String[] args) {
        List<MedicalCare> list = new ArrayList<>();
        list.add(new MedicalCare(0, new Date(), "Dor de cabeca"));
        list.add(new MedicalCare(1, new Date(), "Dor de dente"));
        list.add(new MedicalCare(1, new Date(), "Dor de dente"));
        list.add(new MedicalCare(2, new Date(), "Sangramento nasal"));
        Patient paci = new Patient(new CPF(), "Wills", list);

        System.err.println(paci);

        paci.printAllCare();
    }
}
