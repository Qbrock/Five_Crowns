import java.util.ArrayList;

public class cardHand {
    private ArrayList<card> hand;
    public cardHand() {
        this.hand = new ArrayList<card>();
    }
    public void addCard(card card) {
        //add the card to the hand
        hand.add(card);
    }
    public void removeCard(card card) {
        //remove the card from the hand
        hand.remove(card);
    }
    public void replaceCard(int card, cardDeck deck) {
        //create a for loop that will go through the hand and replace the card with a card from the deck
        hand.set(card, deck.getDeck()[0]);
        deck.removeFirstCard();
    }
    public void setHand(ArrayList<card> hand) {
        this.hand = hand;
    }
    public ArrayList<card> getHand() {
        return hand;
    }
    public card getCard(int i) {
        return hand.get(i);
    }
    public void setCard(int i, card card) {
        hand.set(i, card);
    }
    public int getHandSize() {
        return hand.size();
    }
    public void clearHand() {
        hand.clear();
    }
    public void sortHand() {
        //create a for loop that will go through the hand and sort the cards by suit and number
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size() - 1; j++) {
                if (hand.get(j).getLocation() > hand.get(j + 1).getLocation()) {
                    card temp = hand.get(j);
                    hand.set(j, hand.get(j + 1));
                    hand.set(j + 1, temp);
                }
            }
        }
    }
    public void printHand() {
        //create a for loop that will print out the hand
        for (card card : hand) {
            card.printCard();
        }
    }
}
