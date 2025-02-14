package com.project.entity;

import br.com.caelum.stella.validation.CPFValidator;
import com.project.exception.InvalidCpfException;

public class Cpf {
    private String numberCPF;

    public Cpf() {
    }

    public Cpf(String cpfString) throws InvalidCpfException {
        CPFValidator validator = new CPFValidator();

        try {
            validator.assertValid(cpfString);
        } catch (Exception e) {
            throw new InvalidCpfException("CPF invalido.");
        }

        this.numberCPF = cpfString;
    }

    @Override
    public String toString() {
        // Use StringBuilder para construir a string formatada
        StringBuilder formattedCPF = new StringBuilder();
        formattedCPF.append(numberCPF.charAt(0))
                .append(numberCPF.charAt(1))
                .append(numberCPF.charAt(2))
                .append('.')
                .append(numberCPF.charAt(3))
                .append(numberCPF.charAt(4))
                .append(numberCPF.charAt(5))
                .append('.')
                .append(numberCPF.charAt(6))
                .append(numberCPF.charAt(7))
                .append(numberCPF.charAt(8))
                .append('-')
                .append(numberCPF.charAt(9))
                .append(numberCPF.charAt(10));
        return formattedCPF.toString();
    }

    public String getStringCpf() {
        return numberCPF;
    }

    public void setStringCpf(String cpfString) {

    }
}
