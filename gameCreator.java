import java.util.*;

public class gameCreator {
    final private int playerCount;
    private final Player[] players;
    public gameCreator(int playerCount) {
        this.playerCount = playerCount;
        this.players = new Player[playerCount];
    }
    public String[] getNames() {
        String[] names = new String[playerCount];
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < playerCount; i++) {
            System.out.println("What is the name of player " + (i + 1) + "?");
            names[i] = scanner.nextLine();
        }
        return names;
    }
    public void round_start(cardDeck deck, int round) {
        //create a for loop that will deal the cards to each player
        String[] names = getNames();
        for(int i = 0; i < playerCount; i++) {
            Player player = new Player(names[i]);
            deck.deal(player.getHand(), round);
            players[i] = player;
        }
        System.out.println("Round " + (round - 2) + " has started.");
    }
    public void drawAndReplace(String input, cardDeck deck, Player player, Scanner scanner){
        System.out.println("You drew a " + deck.getFirstCard() + ". Would you like to replace a card or discard it?");
        input = checkValidInput(input, scanner, "replace", "discard", "replace");
        if (input.equals("replace")) {
            System.out.println("Which card would you like to replace?");
            int card_num = Integer.parseInt(scanner.nextLine());
            player.getHand().replaceCard(card_num, deck);
        } else if (input.equals("discard")) {
            deck.removeFirstCard();
        }
    }
    public void round_play(cardDeck deck, int round){
        boolean round_over = false;
        while (!round_over) {
            round_over = round_turns(deck, round, players);
        }
    }
    public String checkValidInput(String input, Scanner scanner, String s1, String s2, String s3) {
        while (!input.equals(s1) && !input.equals(s2) && !input.equals(s3)) {
            input = scanner.nextLine();
            System.out.println("Please enter a valid input.");
        }
        return input;
    }
    public boolean round_turns(cardDeck deck, int round, Player[] players) {
        boolean round_over = false;
        for(Player player : players) {
            System.out.println(player.getName() + "'s turn.");
            player.printHand();
            Scanner scanner = new Scanner(System.in);
            round_over = draw_and_out(scanner, player, deck, round);
        }
        return round_over;
    }
    public boolean draw_and_out(Scanner scanner, Player player, cardDeck deck, int round) {
        boolean round_over = false;
        String input = "";
        while (true) {
            System.out.println("Would you like to draw a card or declare you are out?");
            input = scanner.nextLine();
            input = checkValidInput(input, scanner, "draw", "out", "truly out");
            if (input.equals("out")) {
                try {
                    round_over = win_check(player, round);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("If you are truly out, type truly out.");
            } else if (input.equals("truly out")) {
                round_end(players, round, player);
                round_over = true;
            } else {
                drawAndReplace(input, deck, player, scanner);
                break;
            }
        }

        return round_over;
    }
    public void round_end(Player[] players, int round, Player winner) {
        //create a for loop that will go through each player and add up their points
        //print out the points of each player
        //print out the winner
        for(Player player : players) {
            if (player != winner) {
                player.addPoints();
            } else {
                System.out.println(player.getName() + " won the round!");
            }
            System.out.println(player.getName() + " has " + player.getPoints() + " points.");
        }
    }
    public boolean win_check(Player player, int round) throws CloneNotSupportedException { // make sure to keep going and get the player to draw when not out
        // create cardHand hand and get the player's hand but a copy
        Player copy = (Player) player.clone();
        cardHand hand = copy.getHand();
        hand.sortHand();
        int safe_cards = 0;
        boolean out = false;
        int wild_cards = 0;
        // have copied hand but need to figure out a way to determine whether or not the player is out
        // I could run through this more than once with different parameters, maybe runs first then runs again with the wild cards
        for(int i = 0; i < hand.getHandSize() - 1; i++) {
            String num = hand.getCard(i).getNumber();
            if (num.equals("Joker")) {
                wild_cards++;
            } else {
                if(hand.getCard(i).getNumber().equals(num)) {
                    wild_cards++;
                    hand.setCard(i, new card("Joker", 0));
                }

            }
        }


        if (safe_cards == round - 1) {
            System.out.println("Nice! Your are out.");
            round_end(players, round, player);
            out = true;
        }else if (safe_cards > round) {
            System.out.println("You have more safe cards than the round number. Something went wrong.");
        } else {
            System.out.println("You are not out.");
        }
        return out;
    }
    public Player out_by_number(Player player) {
        cardHand hand = player.getHand();
        hand.sortHand();
        ArrayList<Integer> num_of_groups = new ArrayList<>();
        for(int i = 0; i < hand.getHandSize() - 1; i++) {
            int count = 0;
            String num = hand.getCard(i).getNumber();
            for(int j = i + 1; j < hand.getHandSize() - 1; j++) {
                if(hand.getCard(j).getNumber().equals(num)) {
                    count++;
                    hand.setCard(j, new card("Safe", 0));
                }
            }
        }
        return player;
    }
}
