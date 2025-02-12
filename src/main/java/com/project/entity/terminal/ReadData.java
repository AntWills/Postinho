package com.project.entity.terminal;

import java.util.Scanner;

public class ReadData {
    private static Scanner input = new Scanner(System.in);

    public static int INT() {
        try {
            int aux = input.nextInt();
            input.nextLine();
            return aux;
        } catch (Exception e) {
            System.out.println("Error ao receber INT.\n");
            return -1;
        }
    }

    public static double DOUBLE() {
        try {
            double aux = input.nextDouble();
            input.nextLine();
            return aux;
        } catch (Exception e) {
            System.out.println("Error ao receber DOUBLE.\n");
            return -1;
        }
    }

    public static String STRING() {
        return input.nextLine();
    }

    public static char CHAR() {
        try {
            String charString = ReadData.STRING();
            if (charString.length() > 1) {
                System.out.println("ERROR : len = " + charString.length());
                return '0';
            }
            return charString.charAt(0);
        } catch (Exception e) {
            System.out.println("Error ao receber CHAR.\n");
            return '0';
        }
    }
}
