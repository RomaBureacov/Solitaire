package controller.reader;

/**
 * The scanner object is responsible for reading the input and returning the
 * tokens that it finds to the parser. Not intended to be used outside the parser.
 * @author Roman Bureacov
 * @version 2025-10
 */
public final class Scanner {
    final java.util.Scanner iScan;

    /**
     * Creates a new scanner that will scan the input command and return tokens it finds
     * @param pInput the input command
     */
    Scanner(final String pInput) {
        this.iScan = new java.util.Scanner(pInput);
    }


}
