package controller;

public class IllegalSyntaxException extends Exception {
    final int iLocation;
    final String iCommand;

    public IllegalSyntaxException(final int plocation, final String pCommand) {
        this.iLocation = plocation;
        this.iCommand = pCommand;
    }

    @Override
    public String toString() {
        return "Invalid syntax at %d for %s".formatted(this.iLocation, this.iCommand);
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
