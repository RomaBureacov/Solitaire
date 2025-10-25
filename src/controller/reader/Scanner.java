package controller.reader;

import java.util.HashSet;
import java.util.Set;

/**
 * The scanner object is responsible for reading the input and returning the
 * tokens that it finds to the parser. Not intended to be used outside the parser.
 * @author Roman Bureacov
 * @version 2025-10
 */
public final class Scanner {
    static final Set<Character> SUITES;
    static final Set<Character> RANKS;
    static final Set<String> COMMANDS;

    static {
        SUITES = new HashSet<>(4);
        RANKS = new HashSet<>(13);
        COMMANDS = new HashSet<>();

        SUITES.add('S');
        SUITES.add('D');
        SUITES.add('C');
        SUITES.add('H');

        RANKS.add('A');
        RANKS.add('1');
        RANKS.add('2');
        RANKS.add('3');
        RANKS.add('4');
        RANKS.add('5');
        RANKS.add('6');
        RANKS.add('7');
        RANKS.add('8');
        RANKS.add('9');
        RANKS.add('T');
        RANKS.add('J');
        RANKS.add('Q');
        RANKS.add('K');

        COMMANDS.add("move");
        COMMANDS.add("stack");
        COMMANDS.add("newgame");
        COMMANDS.add("ng");
        COMMANDS.add("newhand");
        COMMANDS.add("new");
        COMMANDS.add("reset");
        COMMANDS.add("help");
        COMMANDS.add("exit");
        COMMANDS.add("q");
    }

    final String iInput;
    int iLocation;

    /**
     * Creates a new scanner that will scan the input command and return tokens it finds
     * @param pInput the input command
     */
    Scanner(final String pInput) {
        super();
        this.iInput = pInput;
    }

    /**
     * Returns and consumes the next input character.
     * @return the next character in the input. If there are
     * no more characters to read, returns the null character (0).
     */
    private char nextChar() {
        if (this.iLocation >= this.iInput.length()) return 0;

        final char lChar = this.iInput.charAt(this.iLocation);
        this.iLocation++;

        return lChar;
    }

    /**
     * Returns but does not consume the next input character.
     * @return the next character in the input. If there are
     * no more characters to read, returns the null character (0).
     */
    private char peekChar() {
        final int lLength = this.iInput.length();
        if (this.iLocation >= lLength) return 0;
        else if (this.iLocation + 1 >= lLength) return 0;

        return this.iInput.charAt(this.iLocation + 1);
    }

    /**
     * Scans the next token available
     * @return the next token
     * @throws IllegalSyntaxException if a bad token is read
     */
    public Token nextToken() throws IllegalSyntaxException {
        char lNext = this.nextChar();
        final StringBuilder lBuffer = new StringBuilder();

        // ignore whitespace
        while (Character.isWhitespace(lNext)) {
            lNext = this.nextChar();
        }

        // is this a stack?
        if (Character.isDigit(lNext)) return new Token(Type.STACK, lNext, this.iLocation);
        if (SUITES.contains(lNext)) return new Token(Type.STACK, lNext, this.iLocation);

        // is this a card?
        return switch(lNext) {
            case 'B', 'R' -> {
                lBuffer.append(lNext);
                lNext = this.nextChar();
                if (SUITES.contains(lNext)) {
                    lBuffer.append(lNext);
                    lNext = this.nextChar();
                    if (RANKS.contains(lNext)) {
                        lBuffer.append(lNext);
                        yield new Token(Type.CARD, lBuffer.toString(), this.iLocation);
                    } else throw new IllegalSyntaxException(this.iLocation, this.iInput);
                } else throw new IllegalSyntaxException(this.iLocation, this.iInput);
            }
            default -> {
                // it must be a command string
                lBuffer.append(lNext);
                while (Character.isAlphabetic(this.peekChar())) {
                    lBuffer.append(this.nextChar());
                }
                if (COMMANDS.contains(lBuffer.toString())) {
                    yield new Token(Type.COMMAND, lBuffer.toString(), this.iLocation);
                } else throw new IllegalSyntaxException(this.iLocation, this.iInput);
            }
        };
    }

    /**
     * Record that represents a token found by the scanner.
     * @param type the token type
     * @param image the token representation
     * @param location the location of the token
     */
    public record Token(Type type, String image, int location) {
        /**
         * Convenience constructor that translates the image character into a string
         * @param pType the token type
         * @param pImage the token representation
         * @param pLocation the location of the token
         */
        Token(final Type pType, final char pImage, final int pLocation) {
            this(pType, String.valueOf(pImage), pLocation);
        }
    }

}
