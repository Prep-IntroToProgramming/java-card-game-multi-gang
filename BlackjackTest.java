import java.util.*;

public class BlackjackTest {
    public static void main(String[] args){
        Blackjack b1 = new Blackjack();
        b1.deal();
        b1.printHands();
        System.out.println("The player has " + b1.value(b1.playerHand));
        System.out.println("The dealer has " + b1.value(b1.dealerHand));
    }
}
