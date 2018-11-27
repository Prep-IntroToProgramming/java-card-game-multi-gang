import java.util.*;

public class BlackjackTest {
    /*
    public static void main(String[] args){
        Blackjack b1 = new Blackjack();
        b1.deal();
        b1.printHands();
        System.out.println("The dealer has " + b1.value(b1.dealerHand));
        System.out.println("The player has " + b1.value(b1.playerHand));
        b1.hit();
        System.out.println("After hitting, player has " + b1.value(b1.playerHand));
        b1.playerHand.printComponents();

        b1.money = 0;
        b1.askBet();

        System.out.println("Adding 50$");

        b1.money = 50;
        b1.askBet(); // ADD Error Statements.
        System.out.println("The bet is " + b1.bet + " and your remaining $ is " + b1.money);
        b1.deal();
        b1.printHands();
        /*
        System.out.println("The dealer has " + b1.value(b1.dealerHand));
        System.out.println("The player has " + b1.value(b1.playerHand));
         
        b1.hit();

        b1.playerHand.clear();

        b1.playerHand.add(new Card(Card.ACE, Card.HEARTS));
        b1.playerHand.add(new Card(Card.ACE, Card.SPADES));
        System.out.println(b1.value(b1.playerHand));

        b1.playerHand.add(new Card(Card.KING, Card.DIAMONDS));
        System.out.println(b1.value(b1.playerHand));

    }*/
    
    
    public static void newMain(){
        Blackjack b1 = new Blackjack();
        Player p1 = new Player();
        //Tests askBet and deal
        p1.askBet();
        b1.deal(p1);
        //Allows to view the hands
        b1.printDealerHand();
        p1.printHand();
        //Sets up a bust to verify the bet is lost in the lose() function
        p1.hit();
        p1.hit();
        //Resets the decks
        p1.hand.clear();
        b1.dealerHand.clear();
        b1.deal(p1);
        //Tests the double down action
        p1.askBet();
        p1.doubleDown();
        //p1.win();
        System.out.println("You have " + p1.money + "$");
    }
}
