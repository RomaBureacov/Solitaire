package controller.reader;

/**
 * Enum that represents the command tokens recognized
 * by the scanner
 * @author Roman Bureacov
 * @version 2025-10
 */
public enum Command {
    /** the move command */         MOVE_COMMAND("move", "stack"),
    /** the new game command */     NEWGAME_COMMAND("newgame, ng"),
    /** the new hand command */     NEWHAND_COMMAND("newhand", "new", "reset"),
    /** the help command */         HELP_COMMAND("help"),
    /** the exit command */         EXIT_COMMAND("exit", "q", "quit"),
    ;

    final String[] iImages;

    Command(final String... pImages) {
        this.iImages = pImages;
    }
}
