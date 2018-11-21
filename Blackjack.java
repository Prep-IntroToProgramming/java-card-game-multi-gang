import java.util.*;

public class Blackjack {
    public static boolean gameOver = false;
    
    Deck mainDeck = new Deck(true);
    Deck playerHand = new Deck(false);
    Deck dealerHand = new Deck(false);
    Blackjack(){
    }
    //Deals out the cards at the start of a game
    void deal(){
        if (mainDeck.count() < 20){
            System.out.print("There aren't enough cards for a new game, so I'll reshuffle the deck");
            mainDeck.resetDeck();
            mainDeck.shuffle();
        }
        dealerHand.add(mainDeck.draw());
        dealerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
    }
    //For debugging/testing
    void printHands(){
        System.out.println("The dealer has:");
        dealerHand.printComponents();
        System.out.println("The player has:");
        playerHand.printComponents();
    }
    //Ends game when user wins
    void win(){
        System.out.println("You win!");
        gameOver = true;
    }
    
    //Ends game when user loses
    void lose(){
        System.out.println("You lose...");
        gameOver = true;
    }
}