package test;

import entities.patient.CPF;
import entities.terminal.ColorOut;

public class TestConsole {
    public static void main(String[] args) {
        CPF cpf = CPF.inTerminal(true, "Digite os dados do CPF:");

        System.out.println("\n\n" + cpf);
    }
}
