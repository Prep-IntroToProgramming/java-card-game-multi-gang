import java.util.*;

public class Blackjack {

    public static boolean gameOver = false;

    public static boolean dealerBlackjack = false;

    public static Deck mainDeck = new Deck(true);
    public static Deck dealerHand = new Deck(false);
    Blackjack(){
        
    }

    //Calculates the value of the dealer's hand
    int dealerValue(){
        int value = 0;
        int aces = 0;
        for (Card currentCard: dealerHand.inDeck){
            //Allows to calculate how much the aces should be at the end
            if (currentCard.blackjackValue == 1){
                aces++;
            }
            value += currentCard.blackjackValue;
        }

        if (value <= 11 && aces > 0) {
            value += 10;
        }
        return value;
    }
    //Deals out the cards at the start of a game
    void deal(Player currentPlayer){
        //empties player's hands
        currentPlayer.hand.clear();
        dealerHand.clear();
        dealerBlackjack = false;
        //Resets and shuffles the deck
        mainDeck.resetDeck();
        mainDeck.shuffle();
        //draws and adds the cards into the hands
        dealerHand.add(mainDeck.draw());
        dealerHand.add(mainDeck.draw());
        currentPlayer.hand.add(mainDeck.draw());
        currentPlayer.hand.add(mainDeck.draw());
        //Looks to see if dealer has blackjack before player does
        if (dealerValue() == 21){
            System.out.println("The dealer has:");
            dealerHand.printComponents();
            System.out.println("He got blackjack");
            dealerBlackjack = true;
        }
    }
    //Does the dealer's turn
    void dealersTurn(){
    }

    //prints out every card the dealer currently has
    void printDealerHand(){
        System.out.println("The dealer has:");
        dealerHand.printComponents();
        
    }

}