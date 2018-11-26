import java.util.*;

public class Player {
    Deck hand = new Deck(false);
    String username = "Player";
    
    void showHand(){
        System.out.println("You have:");
        hand.printComponents();
    }
    
    void addCard(Card c){
        hand.add(c);
    }
    
    void hit(){
        hand.add(Blackjack.mainDeck.draw());
        System.out.println("After hitting, you have:");
        hand.printComponents();
        if (value(hand) > 21){
            System.out.println("You busted!");
            Blackjack.lose();
        }
    }
}
