package com.project.exception;

import br.com.caelum.stella.validation.CPFValidator;

public class UtilCpf {
    private static CPFValidator validator = new CPFValidator();

    public static void validator(String cpf) throws InvalidCpfException {
        try {
            validator.assertValid(cpf);
        } catch (Exception e) {
            throw new InvalidCpfException("CPF invalido.");
        }
    }
}
