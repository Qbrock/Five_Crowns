public class Player implements Cloneable{
    private cardHand hand;
    private int points;
    private final String name;
    public Player(String name) {
        //print out the name of the bot
        this.hand = new cardHand();
        this.name = name;
        this.points = 0;
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //create a function that will take in a card deck and add it to the hand
    public cardHand getHand() {
        return hand;
    }
    public String getName() {
        return name;
    }
    public void printHand() {
        //create a for loop that will print out the hand
        System.out.println(name + "'s hand: ");
        for (card card : hand.getHand()) {
            card.printCard();
        }
    }
    public int getPoints() {
        return points;
    }
    public void addPoints() {
        for(int i = 0; i < hand.getHandSize(); i++) {
            card card = hand.getCard(i);
            switch (card.getNumber()) {
                case "3", "4", "5", "6", "7", "8", "9", "10" -> points += Integer.parseInt(card.getNumber());
                case "Jack" -> points += 11;
                case "Queen" -> points += 12;
                case "King" -> points += 13;
                default -> System.out.println("ERROR: Card number not found.");
            }
        }
    }
}
