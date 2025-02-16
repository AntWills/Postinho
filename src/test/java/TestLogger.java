
// import br.com.caelum.stella.validation.CPFValidator;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
// import org.junit.platform.commons.logging.LoggerFactory;

public class TestLogger {
    // private static final LoggerFactory logger = new LoggerFactory;

    @Test
    public void test() {
        Logger logger = Logger.getLogger(
                TestLogger.class.getName());

        logger.info("📢 Isso é uma mensagem de informação");
        logger.warning("⚠️ Isso é um aviso!");
        logger.severe("🚨 Isso é um erro grave!");

        // for (int i = 1; i <= 10; i++) {
        // logger.info("Deu certo, estou aqui.!! (" + i + ")");
        // }
    }

}
