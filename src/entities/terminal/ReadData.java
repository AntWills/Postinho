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
}
