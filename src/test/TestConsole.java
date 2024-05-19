package test;

// import entities.Terminal;
// import entities.MedicalCare.MedicalCare;

public class TestConsole {
    public static void main(String[] args) {
        // ANSI escape codes para alterar a cor do texto e a cor de fundo
        String reset = "\u001B[0m"; // Reseta as configurações de cor e estilo
        String red = "\u001B[31m"; // Vermelho
        String green = "\u001B[32m"; // Verde
        String yellow = "\u001B[33m"; // Amarelo
        String blue = "\u001B[34m"; // Azul
        String purple = "\u001B[35m"; // Roxo
        String cyan = "\u001B[36m"; // Ciano
        String white = "\u001B[37m"; // Branco
        String black = "\u001B[30m"; // Preto

        String blackBg = "\u001B[40m"; // Fundo preto
        String redBg = "\u001B[41m"; // Fundo vermelho
        String greenBg = "\u001B[42m"; // Fundo verde
        String yellowBg = "\u001B[43m"; // Fundo amarelo
        String blueBg = "\u001B[44m"; // Fundo azul
        String purpleBg = "\u001B[45m"; // Fundo roxo
        String cyanBg = "\u001B[46m"; // Fundo ciano
        String whiteBg = "\u001B[47m"; // Fundo branco

        // Exemplo de texto colorido com cor de fundo
        System.out.println(blackBg + white + "Texto com fundo preto." + reset);
        System.out.println(redBg + white + "Texto com fundo vermelho." + reset);
        System.out.println(greenBg + white + "Texto com fundo verde." + reset);
        System.out.println(yellowBg + white + "Texto com fundo amarelo." + reset);
        System.out.println(blueBg + white + "Texto com fundo azul." + reset);
        System.out.println(purpleBg + white + "Texto com fundo roxo." + reset);
        System.out.println(cyanBg + white + "Texto com fundo ciano." + reset);
        System.out.println(whiteBg + black + "Texto com fundo branco." + reset);
    }
}
