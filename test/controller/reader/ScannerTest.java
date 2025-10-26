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
        // test of the "move" keyword
        this.iScanner = new Scanner("move 0 S");

        Scanner.Token[] lExpected = {
                new Scanner.Token(Type.COMMAND, "move", 4),
                new Scanner.Token(Type.STACK, "0", 6),
                new Scanner.Token(Type.STACK, "S", 8),
        };

        Scanner.Token[] lActual = {
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
        };

        for (int i = 0; i < lExpected.length; i++) {
            assertEquals(lExpected[i].type(), lActual[i].type(), "unequal token types");
            assertEquals(lExpected[i].image(), lActual[i].image(), "unequal token images");
        }

        // test of the "stack" keyword
        this.iScanner = new Scanner("stack 0 S");

        lExpected = new Scanner.Token[]{
                new Scanner.Token(Type.COMMAND, "stack", 4),
                new Scanner.Token(Type.STACK, "0", 6),
                new Scanner.Token(Type.STACK, "S", 8),
        };

        lActual = new Scanner.Token[]{
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
                this.iScanner.nextToken(),
        };

        for (int i = 0; i < lExpected.length; i++) {
            assertEquals(lExpected[i].type(), lActual[i].type(), "unequal token types");
            assertEquals(lExpected[i].image(), lActual[i].image(), "unequal token images");
        }
    }

    /**
     * Test the new hand commands and its variants for recognition
     * @throws IllegalSyntaxException if an error occurs during scanning
     */
    @Test
    public void resetHandCommandTest() throws IllegalSyntaxException {
        // test newhand variant
        this.iScanner = new Scanner("newhand");

        Scanner.Token lExpected = new Scanner.Token(Type.COMMAND, "newhand", 7);
        Scanner.Token lActual = this.iScanner.nextToken();
        
        assertEquals(lExpected.type(), lActual.type(), "unequal token types");
        assertEquals(lExpected.image(), lActual.image(), "unequal token images");

        // test new variant
        this.iScanner = new Scanner("new");

        lExpected = new Scanner.Token(Type.COMMAND, "new", 3);
        lActual = this.iScanner.nextToken();

        assertEquals(lExpected.type(), lActual.type(), "unequal token types");
        assertEquals(lExpected.image(), lActual.image(), "unequal token images");

        // test reset variant
        this.iScanner = new Scanner("reset");

        lExpected = new Scanner.Token(Type.COMMAND, "reset", 5);
        lActual = this.iScanner.nextToken();

        assertEquals(lExpected.type(), lActual.type(), "unequal token types");
        assertEquals(lExpected.image(), lActual.image(), "unequal token images");
    }
}
