package controller.reader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testing class for the scanner.
 * @author Roman Bureacov
 * @version 2025-10
 */
public class ScannerTest {
    private Scanner iScanner;

    /**
     * Tests if the scanner recognizes the tokens of the help command
     * @throws IllegalSyntaxException if syntax error occured during scanning
     */
    @Test
    public void helpCommandTest() throws IllegalSyntaxException {
        this.iScanner = new Scanner("help");

        final Scanner.Token lExpected = new Scanner.Token(Type.COMMAND, "help", 4);
        final Scanner.Token lActual = this.iScanner.nextToken();

        assertEquals(lExpected.type(), lActual.type(), "Unequal types");
        assertEquals(lExpected.image(), lActual.image(), "Unequal images");
    }

    /**
     * Tests if the move command is scanned appropriately.
     * @throws IllegalSyntaxException if there was an error during scanning
     */
    @Test
    public void moveCommandTest() throws IllegalSyntaxException {
        this.iScanner = new Scanner("move 0 S");

        final Scanner.Token[] lExpected = {
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
        };

        final Scanner.Token[] lActual = {
                new Scanner.Token(Type.COMMAND, "move", 4),
                new Scanner.Token(Type.STACK, "0", 6),
                new Scanner.Token(Type.STACK, "S", 8),
        };

        for (int i = 0; i < lExpected.length; i++) {
            assertEquals(lExpected[i].type(), lActual[i].type(), "unequal token types");
            assertEquals(lExpected[i].image(), lActual[i].image(), "unequal token images");
        }
    }
}
