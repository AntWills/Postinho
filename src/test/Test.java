package test;

import entities.CPF;
import entities.MedicalCare.Date;;

public class Test {
    public static void main(String[] args) {
        CPF cpf = new CPF("00100100101");
        System.err.println(cpf);
        System.err.println(cpf.getNumberCPF());

        Date date = new Date("00/00/0000");
        System.err.println("\n\n" + date);

    }
}
