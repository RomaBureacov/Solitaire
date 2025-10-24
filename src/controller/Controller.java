package controller;

import model.Processor;
import model.SolitaireEngine;

/**
 * The controller class handles the control between the view and the game model. Its purpose
 * is to act as the middle-man, and part arbitrary input text into commands. Despite taking in
 * arbitrary strings, it will only recognize certain strings, otherwise exceptions will occur.
 * See the grammar and lexical grammar on command syntax.
 * @author Roman Bureacov
 * @version 2025-10
 */
public class Controller {
    final Processor iGame;

    /**
     * Constructs the controller with its own game model object.
     */
    public Controller() {
        this.iGame = new SolitaireEngine();
    }

    /**
     * Tells the controller to process the input and parse the command.
     * @param pCommand
     */
    public void input(final String pCommand) {
        // begin parsing, which implicitly starts the scanner
    }
}
