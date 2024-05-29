package test;

import entities.patient.CPF;
import entities.patient.MedicalCare.*;

public class Test {
    public static void main(String[] args) {
        CPF cpf = new CPF();
        cpf.in();
        System.out.println(cpf);
    }
}
