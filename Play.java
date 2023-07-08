/**
 * File: Blackjack.java
 * Author: Junnan Shimizu
 * Date: 09/20/2021
 */

/*
 * To run in terminal:
 * javac Play.java
 * java Play.java
 */

import java.util.Scanner;

public class Play {
    public static void main(String[] args){
        // Keeps track of if the game is running
        boolean running = true;
        // Keyboard input in terminal
        Scanner kboard = new Scanner(System.in);
        Blackjack game = new Blackjack(26);
        game.deal();

        //Prints initial player hand
        System.out.println("Your " + game.getPlayerHand().toString());

        // Tests for Blackjack win
        if(game.getPlayerHand().getTotalValue() == 21){
            System.out.println("BLACKJACK! You win!");
            System.exit(0);
        }

        System.out.println("Would you like to hit or stand? Type hit or stand");
        String input = kboard.nextLine();

        //tests the input and executes the choice
        while(running) {
            if(input.toLowerCase().equals("stand"))
                break;

            if(input.toLowerCase().equals("hit")) {
                game.playerDraw();
                if(game.getPlayerHand().getTotalValue() > 21) {
                    System.out.println("Your " + game.getPlayerHand().toString());
                    System.out.println("BUST! YOU LOSE.");
                    System.exit(0);
                } else {
                    System.out.println("Your " + game.getPlayerHand().toString());
                    System.out.println("Would you like to hit or stand? Type hit or stand");
                }
            }
            input = kboard.nextLine();
        }

        //dealer AI
        while(running){
            System.out.println("Dealer's " + game.getDealerHand().toString());
            if (game.getDealerHand().getTotalValue() <= 18) {
                System.out.println("Dealer decides to hit");
                game.dealerDraw();
                if(game.getDealerHand().getTotalValue() > 21){
                    System.out.println("Dealer's " + game.getDealerHand().toString());
                    System.out.println("The Dealer LOSES, You WIN!");
                    System.exit(0);
                }
            } else {
                System.out.println("Dealer decides to stand");
                if(21 - game.getDealerHand().getTotalValue() < 21 - game.getPlayerHand().getTotalValue()){
                    System.out.println("Dealer Wins!");
                    System.exit(0);
                }
                else{
                    System.out.println("You Win!");
                    System.exit(0);
                }
            }
        }
        kboard.close();
    }
}
