package entities.terminal;

import java.util.Scanner;

public class ReadData {
    private static Scanner input = new Scanner(System.in);

    public static int INT() {
        int aux = input.nextInt();
        input.nextLine();
        return aux;
    }

    public static double DOUBLE() {
        double aux = input.nextDouble();
        input.nextLine();
        return aux;
    }

    public static String STRING() {
        return input.nextLine();
    }

    public static char CHAR() {
        String charString = ReadData.STRING();
        if (charString.length() > 1) {
            System.out.println("ERROR : len = " + charString.length());
            return '0';
        }
        return charString.charAt(0);
    }
}
