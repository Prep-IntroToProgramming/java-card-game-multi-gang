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
        //empties player's hands
        playerHand.clear();
        dealerHand.clear();
        //makes sure there's enough cards for the next game
        if (mainDeck.count() < 20){
            System.out.print("There aren't enough cards for a new game, so I'll reshuffle the deck");
            mainDeck.resetDeck();
            mainDeck.shuffle();
        }
        //draws and adds the cards into the hands
        dealerHand.add(mainDeck.draw());
        dealerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
        //Looks to see if dealer has blackjack before player does
        if (checkBlackjack(dealerHand)){
            System.out.println("The dealer has:");
            dealerHand.printComponents();
            System.out.println("He got blackjack");
            lose();
        }
        //Sees if player has blackjack
        else if (checkBlackjack(playerHand)){
            System.out.println("The player has:");
            dealerHand.printComponents();
            System.out.println("You got blackjack!");
            win();
        }
    }
    //Checks to see if someone has blackjack
    boolean checkBlackjack(Deck currentDeck){
        if (currentDeck.check10() && currentDeck.checkRank(1)){
            return true;
        }
        else {
            return false;
        }
    }
    //Calculates the value of the user's hand
    int value(Deck currentDeck){
        int value = 0;
        int aces = 0;
        for (Card currentCard: currentDeck.inDeck){
            //Allows to calculate how much the aces should be at the end
            if (currentCard.blackjackValue == 1){
                aces++;
            }
            else { 
                value += currentCard.blackjackValue;
            }
        }
        //For every ace decides if it should be 11 or 1 and increases value by that
        for (int i = 0; i < aces; i++){
            if (value < 11){
                value += 11;
            }
            else{
                value++;
            }
        }
        return value;
    }
    //Converts any aces in the hand from 11s to 1s

    //Draws a card to the specified deck
    void hit(Deck currentDeck){
        currentDeck.add(mainDeck.draw());
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