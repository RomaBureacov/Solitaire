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
    /** Ace of spades */            BAS("BAS"),
    /** Two of spades */            BA2("BA2"),
    /** Three of spades */          BA3("BA3"),
    /** Four of spades */           BA4("BA4"),
    /** Five of spades */           BA5("BA5"),
    /** Six of spades */            BA6("BA6"),
    /** Seven of spades */          BA7("BA7"),
    /** Eight of spades */          BA8("BA8"),
    /** Nine of spades */           BA9("BA9"),
    /** Ten of spades */            BAT("BAT"),
    /** Jack of spades */           BAJ("BAJ"),
    /** Queen of spades */          BAQ("BAQ"),
    /** King of spades */           BAK("BAK"),
    
    // diamonds
    /** Ace of diamonds */            RDS("RDS"),
    /** Two of diamonds */            RD2("RD2"),
    /** Three of diamonds */          RD3("RD3"),
    /** Four of diamonds */           RD4("RD4"),
    /** Five of diamonds */           RD5("RD5"),
    /** Six of diamonds */            RD6("RD6"),
    /** Seven of diamonds */          RD7("RD7"),
    /** Eight of diamonds */          RD8("RD8"),
    /** Nine of diamonds */           RD9("RD9"),
    /** Ten of diamonds */            RDT("RDT"),
    /** Jack of diamonds */           RDJ("RDJ"),
    /** Queen of diamonds */          RDQ("RDQ"),
    /** King of diamonds */           RDK("RDK"),
    
    // clubs
    /** Ace of clubs */            BCS("BCS"),
    /** Two of clubs */            BC2("BC2"),
    /** Three of clubs */          BC3("BC3"),
    /** Four of clubs */           BC4("BC4"),
    /** Five of clubs */           BC5("BC5"),
    /** Six of clubs */            BC6("BC6"),
    /** Seven of clubs */          BC7("BC7"),
    /** Eight of clubs */          BC8("BC8"),
    /** Nine of clubs */           BC9("BC9"),
    /** Ten of clubs */            BCT("BCT"),
    /** Jack of clubs */           BCJ("BCJ"),
    /** Queen of clubs */          BCQ("BCQ"),
    /** King of clubs */           BCK("BCK"),
    
    // hearts
    /** Ace of hearts */            RHS("RHS"),
    /** Two of hearts */            RH2("RH2"),
    /** Three of hearts */          RH3("RH3"),
    /** Four of hearts */           RH4("RH4"),
    /** Five of hearts */           RH5("RH5"),
    /** Six of hearts */            RH6("RH6"),
    /** Seven of hearts */          RH7("RH7"),
    /** Eight of hearts */          RH8("RH8"),
    /** Nine of hearts */           RH9("RH9"),
    /** Ten of hearts */            RHT("RHT"),
    /** Jack of hearts */           RHJ("RHJ"),
    /** Queen of hearts */          RHQ("RHQ"),
    /** King of hearts */           RHK("RHK"),
    ;

    private final String iFace;

    Card(final String pFace) {
        iFace = pFace;
    }

    /**
     * Gets the face value of the card.
     * @return face value of this card
     */
    public String face() {
        return iFace;
    }
}
