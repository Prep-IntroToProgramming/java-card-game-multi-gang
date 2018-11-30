import java.util.*;

public class BlackjackGame {
    public static void main(String[] args) {
        Blackjack newGame = new Blackjack();

        for (Player onDeck: newGame.gamers) {
            onDeck.askBet();
        }  
        newGame.dealDealer();
        for (Player onDeck: newGame.gamers) {
            newGame.deal(onDeck);
            onDeck.playersTurn();
        }
        newGame.dealersTurn();
        newGame.checkResults();
    }
}