import java.util.Scanner;

public class Main {
    public static String checkValidInt(String input, Scanner scanner) {
        while (!(input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5"))) {
            System.out.println("Please enter a valid number, 2-5.");
            input = scanner.nextLine();
        }
        return input;
    }
    public static int playerIntro() {
        System.out.println("Hello, welcome to Five Crowns.");
        int players = 0;
        Scanner scanner = new Scanner(System.in);
        while(players < 2 || players > 5) {
            System.out.println("How many people do you want to be playing with today?");
            String input = checkValidInt(scanner.nextLine(), scanner);

            players = Integer.parseInt(input);
            if (players < 2 || players > 5) {
                System.out.println("Please enter a number between 2 and 5.");
            }
        }
        return players;
    }
    public static void main(String[] args) {
        int players = playerIntro();
        gameCreator game = new gameCreator(players);
        for(int round = 3; round <= 13; round++) {
            cardDeck deck = new cardDeck();
            deck.shuffle();
            game.round_start(deck, round);
            game.round_play(deck, round);
            System.out.println("Round " + (round - 2) + " has ended.");
        }
    }
}