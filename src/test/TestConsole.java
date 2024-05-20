package test;

import entities.terminal.ColorOut;

public class TestConsole {
    public static void main(String[] args) {
        System.err.println(ColorOut.getText("███████ ░░ ───", ColorOut.blue));
    }
}
