import java.util.*;

public class TestCard {

    public static void main(String[] args){
        int testRank = Card.FOUR;
        int testSuit = Card.HEARTS;
        int deckNumber = 1;
        Card c1 = new Card(testRank, testSuit);
        System.out.println(c1.cardName);

        if (c1.rankString == "Four" && c1.suitString == "Hearts") {
            System.out.println("It's an older card, but it checks out");
        }
        else {
            System.out.println("That waren't supposed te happen");
        }
        /*
        CardTemplate[] cardDeck = new CardTemplate[53];
        for (int i = 1; i != 14; i += 1) {
        for (int j = 1; j < 5; j += 1) {
        cardDeck[deckNumber] = new CardTemplate(i, j);
        deckNumber ++;
        }
        }

        for (int l = 1; l != 53; l ++) {
        System.out.println(cardDeck[l].cardName);
        }

        ArrayList<Card> deckOfCards = new ArrayList(52);
        for (byte i = 1; i < 5; i += 1) {
        for (byte j = 1; j < 14; j += 1) {
        deckOfCards.add(new Card(j, i));
        }
        }
        for (short k = 1; k != 52; k ++) {
        System.out.println(deckOfCards.get(k).cardName);
        }
         */

        Deck d1 = new Deck();
        //d1.printComponents();

        //System.out.println(d1.draw().cardName);
        //System.out.println(d1.draw().cardName);

        //d1.resetDeck();
        //d1.shuffle();
        /*
        Deck d2 = d1.cut();
        d2.printComponents();
        System.out.println("-------Here's the new guys-------");
        d1.printComponents();
        
        d1.resetDeck();
        System.out.println(d1.drawRand().cardName);
        */
        
        System.out.println(d1.check(c1));
        System.out.println(d1.checkSuit(4));
        System.out.println(d1.checkRank(9));
        

    }  
}
