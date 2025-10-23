package model;

/**
 * Enum representing the standard 52 deck of cards.
 * <br>
 * Cards are in a 3-letter format: 
 * <ol>
 *     <li>first letter represents the color</li>
 *     <li>second letter represents the suite</li>
 *     <li>third letter represents the value</li>
 * </ol>
 * <br>
 * As an example, BC5 represents the 5 of clubs.
 *  
 * @author Roman Bureacov
 * @version 2025-10
 */
public enum Card {
    // spades
    /** Ace of spades */				BSA(CardSuite.SPADES, CardRank.ACE),
    /** Two of spades */				BS2(CardSuite.SPADES, CardRank.TWO),
    /** Three of spades */				BS3(CardSuite.SPADES, CardRank.THREE),
    /** Four of spades */				BS4(CardSuite.SPADES, CardRank.FOUR),
    /** Five of spades */				BS5(CardSuite.SPADES, CardRank.FIVE),
    /** Six of spades */				BS6(CardSuite.SPADES, CardRank.SIX),
    /** Seven of spades */				BS7(CardSuite.SPADES, CardRank.SEVEN),
    /** Eight of spades */				BS8(CardSuite.SPADES, CardRank.EIGHT),
    /** Nine of spades */				BS9(CardSuite.SPADES, CardRank.NINE),
    /** Ten of spades */				BST(CardSuite.SPADES, CardRank.TEN),
    /** Jack of spades */				BSJ(CardSuite.SPADES, CardRank.JACK),
    /** Queen of spades */				BSQ(CardSuite.SPADES, CardRank.QUEEN),
    /** King of spades */				BSK(CardSuite.SPADES, CardRank.KING),

    // diamonds
    /** Ace of diamonds */				RDA(CardSuite.DIAMONDS, CardRank.ACE),
    /** Two of diamonds */				RD2(CardSuite.DIAMONDS, CardRank.TWO),
    /** Three of diamonds */			RD3(CardSuite.DIAMONDS, CardRank.THREE),
    /** Four of diamonds */				RD4(CardSuite.DIAMONDS, CardRank.FOUR),
    /** Five of diamonds */				RD5(CardSuite.DIAMONDS, CardRank.FIVE),
    /** Six of diamonds */				RD6(CardSuite.DIAMONDS, CardRank.SIX),
    /** Seven of diamonds */			RD7(CardSuite.DIAMONDS, CardRank.SEVEN),
    /** Eight of diamonds */			RD8(CardSuite.DIAMONDS, CardRank.EIGHT),
    /** Nine of diamonds */				RD9(CardSuite.DIAMONDS, CardRank.NINE),
    /** Ten of diamonds */				RDT(CardSuite.DIAMONDS, CardRank.TEN),
    /** Jack of diamonds */				RDJ(CardSuite.DIAMONDS, CardRank.JACK),
    /** Queen of diamonds */			RDQ(CardSuite.DIAMONDS, CardRank.QUEEN),
    /** King of diamonds */				RDK(CardSuite.DIAMONDS, CardRank.KING),
    
    // clubs
    /** Ace of clubs */			    	BCA(CardSuite.CLUBS, CardRank.ACE),
    /** Two of clubs */			    	BC2(CardSuite.CLUBS, CardRank.TWO),
    /** Three of clubs */				BC3(CardSuite.CLUBS, CardRank.THREE),
    /** Four of clubs */				BC4(CardSuite.CLUBS, CardRank.FOUR),
    /** Five of clubs */				BC5(CardSuite.CLUBS, CardRank.FIVE),
    /** Six of clubs */			    	BC6(CardSuite.CLUBS, CardRank.SIX),
    /** Seven of clubs */				BC7(CardSuite.CLUBS, CardRank.SEVEN),
    /** Eight of clubs */				BC8(CardSuite.CLUBS, CardRank.EIGHT),
    /** Nine of clubs */				BC9(CardSuite.CLUBS, CardRank.NINE),
    /** Ten of clubs */			    	BCT(CardSuite.CLUBS, CardRank.TEN),
    /** Jack of clubs */				BCJ(CardSuite.CLUBS, CardRank.JACK),
    /** Queen of clubs */				BCQ(CardSuite.CLUBS, CardRank.QUEEN),
    /** King of clubs */				BCK(CardSuite.CLUBS, CardRank.KING),

    // hearts
    /** Ace of hearts */				RHA(CardSuite.HEARTS, CardRank.ACE),
    /** Two of hearts */				RH2(CardSuite.HEARTS, CardRank.TWO),
    /** Three of hearts */				RH3(CardSuite.HEARTS, CardRank.THREE),
    /** Four of hearts */				RH4(CardSuite.HEARTS, CardRank.FOUR),
    /** Five of hearts */				RH5(CardSuite.HEARTS, CardRank.FIVE),
    /** Six of hearts */				RH6(CardSuite.HEARTS, CardRank.SIX),
    /** Seven of hearts */				RH7(CardSuite.HEARTS, CardRank.SEVEN),
    /** Eight of hearts */				RH8(CardSuite.HEARTS, CardRank.EIGHT),
    /** Nine of hearts */				RH9(CardSuite.HEARTS, CardRank.NINE),
    /** Ten of hearts */				RHT(CardSuite.HEARTS, CardRank.TEN),
    /** Jack of hearts */				RHJ(CardSuite.HEARTS, CardRank.JACK),
    /** Queen of hearts */				RHQ(CardSuite.HEARTS, CardRank.QUEEN),
    /** King of hearts */				RHK(CardSuite.HEARTS, CardRank.KING);

    
    private final CardRank iRank;
    private final CardSuite iSuite;
    
    Card(final CardSuite pSuite, final CardRank pRank) {
        this.iSuite = pSuite;
        this.iRank = pRank;
    }

    /**
     * Returns the value of the face card.
     * @return 0 for aces, 11 for jacks, 12 for queens, 
     * 13 for kings, and as-is for the rest.
     */
    public CardRank rank() {
        return this.iRank;
    }
    
    public CardSuite suite() {
        return this.iSuite;
    }

}
