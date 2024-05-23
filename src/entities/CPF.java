package entities;

import entities.terminal.ReadData;

public class CPF {
    private String numberCPF;

    public CPF() {
        this.numberCPF = "00000000000";
    }

    public CPF(String cpfString) {
        if (!checkStringCPF(cpfString)) {
            this.numberCPF = "00000000000";
            return;
        }
        this.numberCPF = cpfString;
    }

    public void in() {
        String auxi = "";
        boolean cheking;
        String msg = "Digite apenas os numeros do CPF:";
        do {
            auxi = "";
            System.err.println(msg);
            auxi = ReadData.STRING();

            cheking = !checkStringCPF(numberCPF);

            if (cheking) {
                msg = "\nUm erro foi detectado. Insira os dados novamente:";
            }
        } while (cheking);
        this.numberCPF = auxi;
    }

    private boolean checkStringCPF(String cpfString) {
        if (cpfString.length() != 11) {
            System.err.println("Error. String is not compatible with cpf format.");
            return false;
        }

        char charNumber[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        Boolean auxi;

        for (int i = 0; i < 11; i++) {
            auxi = false;
            for (int j = 0; j < 10; j++) {
                if (cpfString.charAt(i) == charNumber[j]) {
                    auxi = true;
                    break;
                }
            }
            if (!auxi) {
                System.err.println("Error. Letter detected");
                return false;
            }
        }
        return true;
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

    public String getNumberCPF() {
        return numberCPF;
    }

    public void setNumberCPF(String cpfString) {
        if (!checkStringCPF(cpfString)) {
            return;
        }
        this.numberCPF = cpfString;
    }
}
