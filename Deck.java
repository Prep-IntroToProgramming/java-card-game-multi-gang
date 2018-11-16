import java.util.*;

class Deck {
    ArrayList<Card> inDeck = new ArrayList();
    void resetDeck() {
        inDeck.clear();
        for (byte i = 1; i < 5; i += 1) {
            for (byte j = 1; j < 14; j += 1) {
                inDeck.add(new Card(j, i));
            }
        }
    }

    Card draw() {
        Card topCard = inDeck.get(0);
        inDeck.remove(0);
        return topCard;
    }

    Card draw(int drawPos) {
        Card topCard = inDeck.get(drawPos);
        inDeck.remove(drawPos);
        return topCard;
    }

    Card drawRand(){
        Random rand = new Random();
        int cardPos = rand.nextInt(inDeck.size())-1;
        Card transferVar = inDeck.get(cardPos);
        inDeck.remove(cardPos);
        return transferVar;
    }

    int count() {
        return inDeck.size();
    }

    boolean check(Card templateCard){
        int templateRank = templateCard.rankValue;
        int templateSuit = templateCard.suitValue;
        boolean cardIn = false;
        for (Card currentCard: inDeck){
            if (templateRank == currentCard.rankValue && templateSuit == currentCard.suitValue){
                cardIn = true;
            }
        }
        return cardIn;
    }

    boolean checkSuit(int suitCheck){
        boolean cardIn = false;
        for (Card currentCard: inDeck){
            if (suitCheck == currentCard.suitValue){
                cardIn = true;
            }
        }
        return cardIn;
    }

    boolean checkRank(int rankCheck){
        boolean cardIn = false;
        for (Card currentCard: inDeck){
            if (rankCheck == currentCard.rankValue){
                cardIn = true;
            }
        }
        return cardIn;
    }

    void discard() {
        inDeck.remove(0);
    }

    void discard(int discardPos){
        inDeck.remove(discardPos);
    }

    void shuffle() {
        Collections.shuffle(inDeck);
    }

    void add(Card newCard){
        inDeck.add(newCard);
    }

    Deck cut() {
        Deck splitDeck = new Deck(false);
        for (int i = 1; i <= inDeck.size(); i += 1) {
            if (i >= inDeck.size()) {
                ;
            }
            else {
                //System.out.println(i);
                splitDeck.add(draw(i));
            }
        }
        return splitDeck;
    }

    void printComponents() {
        for (int k = 1; k != inDeck.size(); k++){
            System.out.println(inDeck.get(k).cardName);
        }
    }

    Deck() {
        for (byte i = 1; i < 5; i += 1) {
            for (byte j = 1; j < 14; j += 1) {
                inDeck.add(new Card(j, i));
            }
        }
    }

    Deck(boolean buildDeck){

    }
}