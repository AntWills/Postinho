package com.project.entity.patient.MedicalAppointment;

import com.project.entity.terminal.ReadData;
import com.project.entity.terminal.Terminal;

public class Date {
    private int day, month, year;

    public Date() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String dateString) {
        String[] parts = dateString.split("/");

        if (parts.length == 3) {
            try {
                this.day = Integer.parseInt(parts[0]);
                this.month = Integer.parseInt(parts[1]);
                this.year = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Data inválida: " + dateString);
            }
        } else {
            throw new IllegalArgumentException("Formato de data inválido. Use 'dd/mm/yyyy'.");
        }
    }

    public static Date inTerminal() {
        Date date = new Date();

        System.out.print("Digite o dia: ");
        date.setDay(ReadData.INT());
        System.out.print("Digite o mês: ");
        date.setMonth(ReadData.INT());
        System.out.print("Digite o ano: ");
        date.setYear(ReadData.INT());

        return date;
    }

    private static void inTerminal(Date date) {
        System.out.print("Digite o dia: ");
        date.setDay(ReadData.INT());
        System.out.print("Digite o mês: ");
        date.setMonth(ReadData.INT());
        System.out.print("Digite o ano: ");
        date.setYear(ReadData.INT());
    }

    public static Date inTerminal(String msg) {
        Date date = new Date();

        System.out.println(msg);
        Date.inTerminal(date);

        return date;
    }

    public static Date inTerminal(boolean activateLoop, String msg) {
        if (!activateLoop)
            return Date.inTerminal(msg);
        Date date = new Date();

        char confirmation = '0';
        do {
            Terminal.clear();
            System.out.println(msg);
            Date.inTerminal(date);
            Terminal.clear();

            System.out.print("\nA data: " + date.toString() + " está correta? [y][n]");
            confirmation = ReadData.CHAR();
        } while (confirmation != 'y');

        return date;
    }

    @Override
    public String toString() {
        return String.format("%02d", day)
                + "/" + String.format("%02d", month)
                + "/" + String.format("%04d", year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
