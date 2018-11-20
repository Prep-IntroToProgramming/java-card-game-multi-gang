import java.util.*;

public class TestCard {

    public static void main(String[] args){
        int testRank = Card.FOUR;
        int testSuit = Card.HEARTS;
        
        //Makes sure Card is working properly
        Card c1 = new Card(testRank, testSuit);
        System.out.println(c1.cardName);
        
        //Tests draw() function
        Deck d1 = new Deck();
        System.out.println(d1.draw().cardName);
        System.out.println(d1.count());
        
        //Resets deck
        d1.resetDeck();
        System.out.println(d1.count());
        
        //Tests draw(int)
        System.out.println(d1.draw(20).cardName);
        System.out.println(d1.count());
        
        d1.resetDeck();
        
        //Tests drawRand
        System.out.println(d1.drawRand().cardName);
        System.out.println(d1.count());
        
        d1.resetDeck();
        
        //Tests the check functions and discard
        System.out.println(d1.check(new Card(Card.ACE, Card.DIAMONDS)));
        d1.discard();
        System.out.println(d1.check(Card.ACE, Card.DIAMONDS));
        System.out.println(d1.checkSuit(Card.DIAMONDS));
        System.out.println(d1.checkRank(Card.ACE));
        
        d1.resetDeck();
        
        //Tests cut deck and shuffle
        d1.shuffle();
        Deck d2 = d1.cut();
        System.out.println("Here's Deck 1");
        d1.printComponents();
        System.out.println("Here's Deck 2");
        d2.printComponents();
        
        System.out.println(d1.count());
        System.out.println(d2.count());
       
        
    }  
}
