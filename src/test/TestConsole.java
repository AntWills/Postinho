package test;

import entities.terminal.ColorOut;

public class TestConsole {
    public static void main(String[] args) {
        System.err.println(ColorOut.getText("ola mundo", ColorOut.red));
        System.err.println(ColorOut.getText("Ola mundo", ColorOut.black + ColorOut.bgGreen));
        System.err.println(ColorOut.getText("Ola mundo", ColorOut.black + ColorOut.bgRed + ColorOut.bold));
    }
}
