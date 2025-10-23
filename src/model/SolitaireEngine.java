package model;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

/**
 * Class that handles solitaire.
 * <br>
 * See the documentation for relevant opcodes.
 *
 * @author Roman Bureacov
 * @version 2025-10
 */
public final class SolitaireEngine implements Processor {
    /**
     * Collection of publicly recognized opcodes by the Solitaire engine
     */
    public enum Opcode {
        /** NewGame opcode */       NG,
        /** MoveCard opcode */      MC,
        /** DrawCard opcode */      DC,
        /** NewHand opcode */       NH,
        /** AskMoves opcode */      AM,
        /** GetStack opcode */      GS,
        /** Exit opcode */          EX;

        static {
            // lookup table for opcodes, to translate between integer value and the associated opcode
            opcodeTable = Arrays.stream(values())
                    .collect(Collectors.toMap(Enum::ordinal, op -> op));
        }

        private static final Map<Integer, Opcode> opcodeTable;

        /**
         * Translates the integer representation of the opcode to the corresponding enum
         * @param pOpcode the opcode integer
         * @return the Opcode enum value
         */
        static Opcode translate(final Integer pOpcode) {
            final Opcode lOp = opcodeTable.get(pOpcode);

            if (lOp == null) throw new IllegalArgumentException("No such opcode %s".formatted(pOpcode));
            else return lOp;
        }
    }

    /**
     * Collection of enums representing the stack numbers
     */
    public enum Stack {
        /** Board stack 0 */    STACK0,
        /** Board stack 1 */    STACK1,
        /** Board stack 2 */    STACK2,
        /** Board stack 3 */    STACK3,
        /** Board stack 4 */    STACK4,
        /** Board stack 5 */    STACK5,
        /** Board stack 6 */    STACK6,
        /** Aces stack */       SPADES,
        /** Diamonds stack */   DIAMONDS,
        /** Clubs stack */      CLUBS,
        /** Hearts stack */     HEARTS,
        /** Hand stack */       HAND,
        /** Offhand stack */    OFFHAND,
        /** Stagehand stack */  STAGEHAND;

        private static final Map<Integer, Stack> stackTable;

        static {
            stackTable = Arrays.stream(values())
                    .collect(Collectors.toMap(Enum::ordinal, stack -> stack));
        }
        
        static Stack translate(final int pStackNumber) {
            final Stack lStack = stackTable.get(pStackNumber);
            
            if (lStack == null) throw new IllegalArgumentException("No such stack %s".formatted(pStackNumber));
            else return lStack;
        }

    }

    /** The number of stacks that exist in a solitaire game */
    private final static int STACK_COUNT = 7;

    private final Deque<Card> iSpadesPile = new LinkedList<>();
    private final Deque<Card> iDiamondsPile = new LinkedList<>();
    private final Deque<Card> iCLubsPile = new LinkedList<>();
    private final Deque<Card> iHeartsPile = new LinkedList<>();

    private final Deque<Card>[] iStacks = new Deque[STACK_COUNT];

    private final Deque<Card> iHand = new LinkedList<>();
    private final Deque<Card> iOffHand = new LinkedList<>();
    private final Deque<Card> iStageHand = new LinkedList<>();

    public SolitaireEngine() {
        super();
        for (int i = 0; i < STACK_COUNT; i++)
            this.iStacks[i] = new LinkedList<>();

        this.newGame();
    }

    /**
     * Starts a new game.
     * @return null
     */
    @Nullable
    private Object newGame() {
        // shuffle the deck
        this.iHand.clear();
        this.iHand.addAll(newDeck());

        // make the board using the shuffled deck
        for (int i = 0; i < 7; i++) { // 7 stacks
            for (int j = i + 1; j > 0; j--) {
                this.iStacks[i].addLast(this.iHand.removeLast());
            }
        }

        return null;
    }

    /**
     * Helper method that shuffles a standard deck of 52 cards
     * @return the shuffled deck
     */
    private static Deque<Card> newDeck() {
        final Random lGenerator = new Random(System.currentTimeMillis());
        final List<Card> lCards = Arrays.stream(Card.values()).toList();
        final Deque<Card> lShuffledDeck = new LinkedList<>();

        while (!lCards.isEmpty()) {
            lShuffledDeck.addLast(lCards.get(lGenerator.nextInt(lCards.size())));
        }

        return lShuffledDeck;
    }

    /**
     * Moves a card from one pile to another
     * @param pStackOrigin the stack to move from
     * @param pStackDest the stack to move to
     * @return Boolean true if the card successfully moved to the pile, Boolean false otherwise
     */
    private Object moveCard(final int pStackOrigin, final int pStackDest) {
        final Deque<Card> lStack = this.getStackInternal(Stack.translate(pStackOrigin));
        final Stack lDestStackNumber = Stack.translate(pStackDest);
        final Deque<Card> lDestStack = this.getStackInternal(lDestStackNumber);

        final Card lCard = lStack.removeLast();
        final Card lUnderCard = lDestStack.peekLast();

        switch (lDestStackNumber) {
            case STACK0, STACK1, STACK2, STACK3, STACK4, STACK5, STACK6 -> {
                if (lUnderCard == null) { // is the destination empty?
                    if (lCard.rank() == CardRank.KING) {
                        lDestStack.addLast(lCard);
                        return true;
                    }
                } else if (lCard.rank().ordinal() == lUnderCard.rank().ordinal() - 1) { // else as normal...
                    if (lCard.name().charAt(0) != lUnderCard.name().charAt(0)) { // if they're of differing color
                        lDestStack.addLast(lCard);
                        return true;
                    }
                }
            }
            case Stack.SPADES -> {
                if (this.stackToAces(CardSuite.SPADES, lDestStack, lCard, lUnderCard)) return true;
            }
            case Stack.DIAMONDS -> {
                if (this.stackToAces(CardSuite.DIAMONDS, lDestStack, lCard, lUnderCard)) return true;
            }
            case Stack.CLUBS -> {
                if (this.stackToAces(CardSuite.CLUBS, lDestStack, lCard, lUnderCard)) return true;
            }
            case Stack.HEARTS -> {
                if (this.stackToAces(CardSuite.HEARTS, lDestStack, lCard, lUnderCard)) return true;
            }
            case HAND, OFFHAND, STAGEHAND -> { }
        };

        // card failed to stack
        lStack.addLast(lCard);
        return false;
    }

    /**
     * Helper method to decide to stack to aces
     * @param pTargetSuite the two-letter aces stack name (e.g.: black spades = "BS")
     * @param pDestStack the destination card deque
     * @param pCard the card
     * @param pUnderCard the card to stack onto
     * @return true if the card stacks, false otherwise
     */
    private boolean stackToAces(final CardSuite pTargetSuite, final Deque<Card> pDestStack,
                                final Card pCard, final Card pUnderCard) {
        if (pCard.suite() == pTargetSuite) {
            if (pUnderCard == null) { // if there are no cards on the suite stack
                if (pCard.rank() == CardRank.ACE) {
                    pDestStack.addLast(pCard);
                    return true;
                }
            } else if (pCard.rank().ordinal() == pUnderCard.rank().ordinal() - 1) { // otherwise there are cards to work with
                pDestStack.addLast(pCard);
                return true;
            }
        }

        return false;
    }

    /**
     * Draws cards from the hand
     * @param pCardCount the number of cards to draw, must be 1 or 3
     * @return Boolean true if there were cards to draw from the hand, Boolean false otherwise
     */
    private Object drawCard(final int pCardCount) {
        if (pCardCount != 1 && pCardCount != 3)
            throw new IllegalArgumentException(
                    "Card drawing must happen in 1 or 3 at a time (provided: %s).".formatted(pCardCount)
            );

        if (this.iHand.isEmpty()) return false;

        // stow remaining cards on stage to offhand
        while (!this.iStageHand.isEmpty())
            this.iOffHand.addLast(this.iStageHand.removeFirst());

        // draw cards from the top of hand
        for (int i = 0; i < pCardCount; i++)
            this.iStageHand.addLast(this.iHand.removeLast());

        return true;
    }

    /**
     * Gets a new hand, transferring all cards from the stagehand
     * and the offhand to the hand
     * @return null
     */
    @Nullable
    private Object newHand() {
        // stagehand back to hand
        while (!this.iStageHand.isEmpty())
            this.iHand.addLast(this.iStageHand.removeFirst());

        // offhand back to hand
        while (!this.iOffHand.isEmpty())
            this.iHand.addLast(this.iOffHand.removeFirst());

        return null;
    }

    /**
     * Asks if there are any remaining moves that may be made.
     * @return Boolean true if there are possible moves, Boolean false otherwise
     */
    private Object askMoves(final int pDrawMode) {
        // TODO: how do we ask for moves?
        // can we position the hand onto the aces stacks?

        // can we position the hand onto the stacks?

        // can we position any cards from the stacks onto the aces stacks?
        return null;
    }

    /**
     * Gets the stack specified
     * @param pStackCode the stack number
     * @return a copy of the stack as an array of cards
     */
    private Object getStack(final int pStackCode) {
        return this.getStackInternal(Stack.translate(pStackCode)).toArray();
    }

    /**
     * Gets the internal stack used for game logic.
     * @param pStackCode the enum representing the stack
     * @return the Deque of cards
     */
    private Deque<Card> getStackInternal(final Stack pStackCode) {
        return switch (pStackCode) {
            case STACK0, STACK1, STACK2, STACK3, STACK4, STACK5, STACK6 ->
                    this.iStacks[pStackCode.ordinal()];
            case SPADES -> this.iSpadesPile;
            case DIAMONDS -> this.iDiamondsPile;
            case CLUBS -> this.iCLubsPile;
            case HEARTS -> this.iHeartsPile;
            case HAND -> this.iHand;
            case OFFHAND -> this.iOffHand;
            case STAGEHAND -> this.iStageHand;
        };
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
    public Object command(final Integer pOpcode, final Integer pArg1, final Integer pArg2) {

        return switch(Opcode.translate(pOpcode)) {
            case NG -> this.newGame();
            case MC -> this.moveCard(pArg1, pArg2);
            case DC -> this.drawCard(pArg1);
            case NH -> this.newHand();
            case AM -> this.askMoves(pArg1);
            case GS -> this.getStack(pArg1);
            case EX -> this.exit();
            default -> throw new IllegalArgumentException("Unknown opcode %s".formatted(pOpcode));
        };
    }

}
