package entities.terminal;

public class Terminal {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pause() {
        System.err.print("\nPressione [ENTER] para continuar...");
        ReadData.STRING();
    }
}
