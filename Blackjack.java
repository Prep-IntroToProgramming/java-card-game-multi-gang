import java.util.*;

public class Blackjack {

    public ArrayList<Player> gamers = new ArrayList<Player>();

    public static boolean dealerBlackjack = false;

    public static Deck mainDeck = new Deck(true);
    public static Deck dealerHand = new Deck(false);
    Blackjack(){
        int playerCount;
        //Gets number of players
        System.out.println("How many gamers do we have today?");
        Scanner userInput = new Scanner(System.in);
        while (!userInput.hasNextInt()) {
            System.out.println("Please enter a valid # of players.");
            userInput.nextLine();
        }
        playerCount = userInput.nextInt();
        while (playerCount < 1 || playerCount > 5){
            System.out.println("Please enter a valid # of players (1-5)");
            while (!userInput.hasNextInt()) {
                System.out.println("It would help if it was a number");
                userInput.nextLine();
            }
            playerCount = userInput.nextInt();
        }
        System.out.println("Great! " + playerCount + " gamers are playing.");
        //For every player makes a player object and adds it to the list of players
        for (int i = 0; i < playerCount; i++) {
            gamers.add(new Player());
            gamers.get(i).username = "Player " + Integer.toString(i + 1); 
        }
    }
    //Calculates the value of the dealer's hand
    int dealerValue(){
        int value = 0;
        int aces = 0;
        for (Card currentCard: dealerHand.inDeck){
            //Allows to calculate how much the aces should be at the end
            if (currentCard.blackjackValue == 1){
                aces++;
            }
            value += currentCard.blackjackValue;
        }

        if (value <= 11 && aces > 0) {
            value += 10;
        }
        return value;
    }

    void dealDealer(){
        dealerHand.clear();
        dealerBlackjack = false;
        //Resets and shuffles the deck
        mainDeck.resetDeck();
        mainDeck.shuffle();
        dealerHand.add(mainDeck.draw());
        dealerHand.add(mainDeck.draw());
        System.out.println("The dealer is showing: " + dealerHand.inDeck.get(0).cardName);
        //Looks to see if dealer has blackjack before player does
        if (dealerValue() == 21){
            System.out.println("The dealer has: " + dealerValue());
            dealerHand.printComponents();
            System.out.println("He got blackjack");
            dealerBlackjack = true;
        }
    }
    //Deals out the cards to a player start of a game
    void deal(Player currentPlayer){
        //empties player's hands
        currentPlayer.hand.clear();
        //draws and adds the cards into the hands
        currentPlayer.hand.add(mainDeck.draw());
        currentPlayer.hand.add(mainDeck.draw());
        //Looks to see if player has blackjack
        if (currentPlayer.value() == 21){
            currentPlayer.hasBlackjack = true;
        }
    }
    //Does the dealer's turn
    void dealersTurn(){
        System.out.println("The dealer currently has: " + dealerValue());
        System.out.println(dealerHand.inDeck.get(0).cardName);
        System.out.println(dealerHand.inDeck.get(1).cardName);
        while (dealerValue() < 17) {
            Card currentCard = mainDeck.draw();
            dealerHand.add(currentCard);
            System.out.println("The dealer drew " + currentCard.cardName + ": " + dealerValue());
        }
    }

    //prints out every card the dealer currently has
    void printDealerHand(){
        System.out.println("The dealer has: " + dealerValue());
        dealerHand.printComponents();
    }

    void checkResults(){
        for (Player currentPlayer: gamers){
            //Excludes players who already busted out from judgement
            if (currentPlayer.value() > 21){
            }
            else if (dealerBlackjack) {
                currentPlayer.lose();
            }
            //If dealer busted or they got more than dealer they win
            else if ((currentPlayer.value() > dealerValue()) || dealerValue() > 21){
                currentPlayer.win();
            }
            //If they got less than dealer they lose
            else if (currentPlayer.value() < dealerValue()){
                currentPlayer.lose();
            }
            //If they get the same as the dealer they tie
            else if (currentPlayer.value() == dealerValue()){
                currentPlayer.push();
            }
            //Generic error statement
            else{
                System.out.println("I don't quite know how this happened.");
            }
            
            currentPlayer.reset();
        }
    }
}

