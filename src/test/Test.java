package test;

import entities.CPF;

public class Test {
    public static void main(String[] args) {
        CPF cpf = new CPF("00100100101");
        System.err.println(cpf);
        System.err.println(cpf.getNumberCPF());

    }
}
