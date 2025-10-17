package model;

import org.jetbrains.annotations.Nullable;

/**
 * Class that handles solitaire.
 * @author Roman Bureacov
 * @version 2025-10
 */
public final class SolitaireEngine implements Processor {
    /**
     * Collection of publicly recognized opcodes by the Solitaire engine
     */
    public enum Opcode {
        /** NewGame opcode */       NG(0),
        /** MoveCard opcode */      MC(1),
        /** DrawCard opcode */      DC(2),
        /** NewHand opcode */       NH(3),
        /** AskMoves opcode */      AM(4),
        /** GetPile opcode */       GP(5),
        /** Exit opcode */          EX(9);


        private final int iCode;

        Opcode(final int pCode) {
            this.iCode = pCode;
        }

        /**
         * Access the code integer value for this code.
         * @return the code integer
         */
        public int code() {
            return iCode;
        }

        static Opcode translate(final int pOpcode) {
            return switch (pOpcode) {
                case 0 -> NG;
                case 1 -> MC;
                case 2 -> DC;
                case 3 -> NH;
                case 4 -> AM;
                case 5 -> GP;
                case 9 -> EX;
                default -> throw new IllegalArgumentException("Unknown opcode %s".formatted(pOpcode));
            };
        }
    }

    /**
     * Starts a new game.
     * @return null
     */
    @Nullable
    private Object newGame() {
        return null;
    }

    /**
     * Moves a card from one pile to another
     * @param pPileOrigin the pile to move from
     * @param pPileDest the pile to move onto
     * @return Boolean true if the card successfully moved to the pile, Boolean false otherwise
     */
    private Object moveCard(final int pPileOrigin, final int pPileDest) {
        return null;
    }

    private Object drawCard(final int pPileCode) {
        return false;
    }

    /**
     * Gets a new hand.
     * @return null
     */
    @Nullable
    private Object newHand() {
        return null;
    }

    /**
     * Asks if there are any remaining moves that may be made.
     * @return Boolean true if there are possible moves, Boolean false otherwise
     */
    private Object askMoves() {
        return null;
    }

    /**
     * Gets the pile specified
     * @param pPileCode the pile code
     * @return the pile as an array of cards
     */
    private Object getPile(final int pPileCode) {
        return null;
    }

    /**
     * Calls the system to exit the game
     * @return null
     */
    @Nullable
    private Object exit() {
        return null;
    }

    @Override
    public Object command(final int pOpcode, final int pArg1, final int pArg2) {

        return switch(Opcode.translate(pOpcode)) {
            case NG -> this.newGame();
            case MC -> this.moveCard(pArg1, pArg2);
            case DC -> this.drawCard(pArg1);
            case NH -> this.newHand();
            case AM -> this.askMoves();
            case GP -> this.getPile(pArg1);
            case EX -> this.exit();
            default -> throw new IllegalArgumentException("Unknown opcode %s".formatted(pOpcode));
        };
    }

    @Override
    public Object command(final int[] pInstruction) {
        return this.command(pInstruction[0], pInstruction[1], pInstruction[2]);
    }
}
