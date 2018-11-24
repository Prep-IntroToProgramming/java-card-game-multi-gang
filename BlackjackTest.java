import java.util.*;

public class BlackjackTest {
    public static void main(String[] args){
        Blackjack b1 = new Blackjack();
        b1.deal();
        b1.printHands();
        System.out.println("The dealer has " + b1.value(b1.dealerHand));
        System.out.println("The player has " + b1.value(b1.playerHand));
        b1.hit(b1.playerHand);
        System.out.println("After hitting, player has " + b1.value(b1.playerHand));
        b1.playerHand.printComponents();
        
        b1.askBet();
        System.out.println("The bet is " + b1.bet + " and your remaining $ is " + b1.money);
    }
}
