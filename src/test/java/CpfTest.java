import br.com.caelum.stella.validation.CPFValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// mvn test -Dtest=CpfTest#testCpfValido -Dsurefire.useFile=false
// mvn -> para maven
// test -> para teste
// -Dtest=CpfTest#testCpfValido -> -Dtest=[Classe]#[Método]
// -Dsurefire.useFile=false -> Permitir que print saia no "Degub console" eno "Terminal"

public class CpfTest {

    @Test
    public void testCpfValido() {
        CPFValidator validator = new CPFValidator();
        String cpfValido = "123.456.789-09"; // Substitua por um CPF real válido

        System.err.println("Aqui");
        System.err.println("Aqui\n");
        System.err.println("Aqui");
        assertDoesNotThrow(() -> validator.assertValid(cpfValido), "CPF deveria ser válido");
    }

    @Test
    public void testCpfInvalido() {
        CPFValidator validator = new CPFValidator();
        String cpfInvalido = "111.222.333-44"; // CPF inválido

        assertThrows(Exception.class, () -> validator.assertValid(cpfInvalido), "CPF deveria ser inválido");
    }
}
