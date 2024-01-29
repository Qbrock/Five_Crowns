public class card {
    //create a constructor that takes in a string
    private final String number;
    private String suit;
    private int location;

    public card(String n, int loc) {
        this.number = n.split(",")[0];
        if(!this.number.equals("Joker") && !this.number.equals("test") && !this.number.equals("Safe")) {
            this.suit = n.split(",")[1];
        }
        //create a set of if conditions that will add the number and suit of the card into an int
        this.location = loc;
    }
    public String getNumber() {
        return number;
    }
    public String getSuit() {
        return suit;
    }
    //specifically for deck location not hand location
    public int getLocation() {
        return location;
    }
    public void setLocation(int location) {
        this.location = location;
    }
    public void printCard() {
        //print out the card
        if(number.equals("Joker")) {
            System.out.println(number);
            return;
        }
        System.out.println(number + " of " + suit);
    }
}
