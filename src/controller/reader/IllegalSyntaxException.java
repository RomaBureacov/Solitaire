package controller.reader;

public class IllegalSyntaxException extends Exception {
    final int iLocation;
    final String iCommand;

    public IllegalSyntaxException(final int plocation, final String pCommand) {
        this.iLocation = plocation;
        this.iCommand = pCommand;
    }

    @Override
    public String toString() {
        final String lMsg = "Invalid Syntax: ";
        final int lPad = lMsg.length() + this.iLocation;
        return """
               Invalid syntax: %s
               %*s
               """
                .stripIndent()
                .formatted(this.iCommand, lPad, "^");
    }

    /**
     * The location of the problematic character
     * @return the character position that raised the exception during scanning
     */
    public int location() {
        return this.iLocation;
    }

    /**
     * The problematic command.
     * @return the command that raised the exception.
     */
    public String command() {
        return this.iCommand;
    }
}
