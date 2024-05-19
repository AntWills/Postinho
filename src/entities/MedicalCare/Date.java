package entities.MedicalCare;

import entities.terminal.ReadData;

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

    public void in() {
        System.err.print("\nDigite o dia: ");
        this.day = ReadData.INT();
        System.err.print("Digite o mês: ");
        this.day = ReadData.INT();
        System.err.print("Digite o ano: ");
        this.day = ReadData.INT();
    }

    @Override
    public String toString() {
        return String.format("%02d", day)
                + " : " + String.format("%02d", month)
                + " : " + String.format("%04d", year);
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
