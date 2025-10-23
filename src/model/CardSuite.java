package model;

/**
 * Enum that represents the card suite with color.
 * @author Roman Bureacov
 * @version 2025-10
 */
public enum CardSuite {
    /** Spades, black */        SPADES(CardColor.BLACK),
    /** Diamonds, red */        DIAMONDS(CardColor.RED),
    /** Clubs, black */         CLUBS(CardColor.BLACK),
    /** Hearts, red */          HEARTS(CardColor.RED);

    private CardColor iColor;

    CardSuite(final CardColor pColor) {
        this.iColor = pColor;
    }
}
