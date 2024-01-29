public class cardDeck {
    //create a constructor that takes in a string
    private card[] deck;
    public cardDeck() {
        String[] cardDeck = {
                //create strings for every suit from 3 to king with an extra suit that is called Stars and format it like this: "3,H"
                "3,Hearts", "4,Hearts", "5,Hearts", "6,Hearts", "7,Hearts", "8,Hearts", "9,Hearts", "10,Hearts", "Jack,Hearts", "Queen,Hearts", "King,Hearts", "Ace,Hearts",
                "3,Diamonds", "4,Diamonds", "5,Diamonds", "6,Diamonds", "7,Diamonds", "8,Diamonds", "9,Diamonds", "10,Diamonds", "Jack,Diamonds", "Queen,Diamonds", "King,Diamonds", "Ace,Diamonds",
                "3,Clubs", "4,Clubs", "5,Clubs", "6,Clubs", "7,Clubs", "8,Clubs", "9,Clubs", "10,Clubs", "Jack,Clubs", "Queen,Clubs", "King,Clubs", "Ace,Clubs",
                "3,Spades", "4,Spades", "5,Spades", "6,Spades", "7,Spades", "8,Spades", "9,Spades", "10,Spades", "Jack,Spades", "Queen,Spades", "King,Spades", "Ace,Spades",
                "3,Stars", "4,Stars", "5,Stars", "6,Stars", "7,Stars", "8,Stars", "9,Stars", "10,Stars", "Jack,Stars", "Queen,Stars", "King,Stars", "Ace,Stars",
                "Joker,", "Joker,","Joker,", "Joker,"
        };
        this.deck = new card[cardDeck.length];
        for (int i = 0; i < cardDeck.length; i++) {
            card card = new card(cardDeck[i], i);
            this.deck[i] = card;
        }
    }
    public card[] getDeck() {
        return deck;
    }
    public void setDeck(card[] deck) {
        this.deck = deck;
    }
    public void shuffle() {
        //create a for loop that will go through the deck and swap the cards with a random card
        for (int i = 0; i < deck.length; i++) {
            int random = (int) (Math.random() * deck.length);
            card temp = deck[i];
            deck[i] = deck[random];
            deck[random] = temp;
        }
    }
    public String getFirstCard() {
        //create a for loop that will print out the deck
        if(deck[0].getNumber().equals("Joker")) {
            return "Joker";
        }
        return deck[0].getNumber() + " of " + deck[0].getSuit();
    }
    public void removeFirstCard() {
        card[] newDeck = new card[deck.length - 1];
        System.arraycopy(deck, 1, newDeck, 0, newDeck.length);
        this.deck = newDeck;
    }
    public void deal(cardHand hand, int round) {
        hand.clearHand();
        for (int i = 0; i < round; i++) {
            hand.addCard(deck[i]);
            removeFirstCard();
        }
    }
}
