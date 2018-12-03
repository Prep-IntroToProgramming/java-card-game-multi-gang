import java.util.*;

public class BlackjackGame {
    public static void main(String[] args) {
        boolean playing = true;
        Blackjack newGame = new Blackjack();

        while (playing){
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
            //Asks if the players want to go again
            System.out.println("Would you like to play again? Y/N");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if (answer.toLowerCase().contains("y")){
                System.out.println("Great, let's go again");
            }
            else {
                playing = false;
                System.out.println("Okay, see ya");
            }
            
        }
    }
}