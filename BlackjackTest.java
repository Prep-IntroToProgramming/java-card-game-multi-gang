import java.util.*;

public class BlackjackTest {
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
         */
        b1.hit();

        b1.playerHand.clear();

        b1.playerHand.add(new Card(Card.ACE, Card.HEARTS));
        b1.playerHand.add(new Card(Card.ACE, Card.SPADES));
        System.out.println(b1.value(b1.playerHand));

        b1.playerHand.add(new Card(Card.KING, Card.DIAMONDS));
        System.out.println(b1.value(b1.playerHand));

    }
}
