import org.junit.Test;
import static org.junit.Assert.*;


public class PasswordCheckerTest {

    @Test
    public void passwordCorrecto() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        int resultado = checker.check("abcdefgh", "abcdefgh");
        assertEquals(PasswordChecker.PASSWORD_OK, resultado);
    }

    @Test
    public void passwordVacia() {
        PasswordChecker checker = new PasswordChecker();
        int resultado = checker.check("", "");
        assertEquals(PasswordChecker.ERR_BLANK, resultado);
    }

    @Test
    public void passwordEnBlanco() {
        PasswordChecker checker = new PasswordChecker();
        int resultado = checker.check("   ", "   ");
        assertEquals(PasswordChecker.ERR_BLANK, resultado);
    }

    @Test
    public void passwordDemasiadoCorta() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        int resultado = checker.check("abc", "abc");
        assertEquals(PasswordChecker.ERR_LENGTH, resultado);
    }

    @Test
    public void passwordDemasiadoLarga() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        int resultado = checker.check("abcdefghijklm", "abcdefghijklm");
        assertEquals(PasswordChecker.ERR_LENGTH, resultado);
    }

    @Test
    public void passwordSinNumeros() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        checker.setCheckDigits(true);
        int resultado = checker.check("abcdefgh", "abcdefgh");
        assertEquals(PasswordChecker.ERR_DIGITS, resultado);
    }

    @Test
    public void passwordSinMinusculas() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        checker.setCheckLowers(true);
        int resultado = checker.check("ABCDEFG1", "ABCDEFG1");
        assertEquals(PasswordChecker.ERR_LOWERS, resultado);
    }

    @Test
    public void passwordSinMayusculas() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        checker.setCheckUppers(true);
        int resultado = checker.check("abcdefg1", "abcdefg1");
        assertEquals(PasswordChecker.ERR_UPPERS, resultado);
    }

    @Test
    public void passwordNoCoincide() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        int resultado = checker.check("abcdefgh", "abcdefgi");
        assertEquals(PasswordChecker.ERR_NO_MATCH, resultado);
    }

    @Test
    public void longitudMinimaNegativaLanzaExcepcion() {
        PasswordChecker checker = new PasswordChecker();
        assertThrows(IllegalArgumentException.class, () -> checker.setMinLength(-1));
    }

    @Test
    public void longitudMaximaMenorQueMinimaLanzaExcepcion() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        assertThrows(IllegalArgumentException.class, () -> checker.setMaxLength(5));
    }
    @Test
    public void passwordExactamenteMaximoEsValida() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        checker.setMaxLength(12);
        int resultado = checker.check("abcdefghijkl", "abcdefghijkl");
        assertEquals(PasswordChecker.PASSWORD_OK, resultado);
    }

    @Test
    public void ordenComprobacionBlancoYNoCoincide() {
        PasswordChecker checker = new PasswordChecker();
        int resultado = checker.check("", "abc");
        assertEquals(PasswordChecker.ERR_BLANK, resultado);
    }

    @Test
    public void ordenComprobacionLongitudYNoCoincide() {
        PasswordChecker checker = new PasswordChecker();
        checker.setMinLength(8);
        int resultado = checker.check("abc", "xyz");
        assertEquals(PasswordChecker.ERR_LENGTH, resultado);
    }

    @Test
    public void setMinLengthCero() {
        PasswordChecker checker = new PasswordChecker();
        assertThrows(IllegalArgumentException.class, () -> checker.setMinLength(0));
    }

}