package controller.reader;

public class IllegalSyntaxException extends Exception {
    final String iMessage;
    final int iLocation;
    final String iCommand;

    /**
     * Creates an illegal syntax exception with a generic message prefix
     * "Unknown Syntax: ".
     * @param pLocation the location of the error
     * @param pCommand the command in question
     */
    public IllegalSyntaxException(final int pLocation, final String pCommand) {
        this("Unknown Syntax: ", pLocation, pCommand);
    }

    /**
     * Creates an Illegal syntax exception with a prefix message.
     * @param pMessage the message
     * @param pLocation the location of the error
     * @param pCommand the command in question
     */
    public IllegalSyntaxException(final String pMessage, final int pLocation, final String pCommand) {
        super();
        this.iMessage = pMessage;
        this.iLocation = pLocation;
        this.iCommand = pCommand;
    }

    @Override
    public String toString() {
        final int lPad = this.iMessage.length() + this.iLocation;
        return ("%s%s"
                + "\n%" + lPad + "s")
                .formatted(this.iMessage, this.iCommand, "^");
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
