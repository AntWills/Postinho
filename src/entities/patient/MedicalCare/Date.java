package entities.patient.MedicalCare;

import entities.terminal.ReadData;
import entities.terminal.Terminal;

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

    public void in(String msg) {
        char confirmation = '1';
        do {
            if (confirmation == '1')
                Terminal.clear();
            System.out.println(msg);
            System.out.print("Digite o dia: ");
            this.day = ReadData.INT();
            System.out.print("Digite o mês: ");
            this.day = ReadData.INT();
            System.out.print("Digite o ano: ");
            this.day = ReadData.INT();
            Terminal.clear();

            System.out.print("\nA data: " + this.toString() + " está correta? [y][n]");
            confirmation = ReadData.CHAR();
        } while (confirmation != 'y');
    }

    public void in(boolean confirmation, String msg) {
        if (confirmation) {
            this.in(msg);
            return;
        }
        System.out.println(msg);
        System.out.print("Digite o dia: ");
        this.day = ReadData.INT();
        System.out.print("Digite o mês: ");
        this.day = ReadData.INT();
        System.out.print("Digite o ano: ");
        this.day = ReadData.INT();
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
