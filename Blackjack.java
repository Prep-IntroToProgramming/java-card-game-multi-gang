import java.util.*;

public class Blackjack {

    public static boolean gameOver = false;

    public int money = 50;
    public int bet = 0;
    public boolean gambling = false;

    public boolean doubledDown = false;

    Deck mainDeck = new Deck(true);
    Deck playerHand = new Deck(false);
    Deck dealerHand = new Deck(false);
    Blackjack(){
    }
    //Deals out the cards at the start of a game
    void deal(){
        //empties player's hands
        playerHand.clear();
        dealerHand.clear();
        //makes sure there's enough cards for the next game
        if (mainDeck.count() < 20){
            System.out.print("There aren't enough cards for a new game, so I'll reshuffle the deck");
            mainDeck.resetDeck();
            mainDeck.shuffle();
        }
        //draws and adds the cards into the hands
        dealerHand.add(mainDeck.draw());
        dealerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
        playerHand.add(mainDeck.draw());
        //Looks to see if dealer has blackjack before player does
        if (checkBlackjack(dealerHand)){
            System.out.println("The dealer has:");
            dealerHand.printComponents();
            System.out.println("He got blackjack");
            lose();
        }
        //Sees if player has blackjack
        else if (checkBlackjack(playerHand)){
            System.out.println("The player has:");
            dealerHand.printComponents();
            System.out.println("You got blackjack!");
            win();
        }
    }
    //Checks to see if someone has blackjack
    boolean checkBlackjack(Deck currentDeck){
        if (currentDeck.check10() && currentDeck.checkRank(1)){
            return true;
        }
        else {
            return false;
        }
    }
    //Calculates the value of the user's hand
    int value(Deck currentDeck){
        int value = 0;
        byte aces = 0;
        for (Card currentCard: currentDeck.inDeck){
            //Allows to calculate how much the aces should be at the end
            if (currentCard.blackjackValue == 1){
                aces++;
            }
            else { 
                value += currentCard.blackjackValue;
            }
        }
        //For every ace decides if it should be 11 or 1 and increases value by that
        for (byte i = 0; i < aces; i++){
            if (value < 11){
                value += 11;
            }
            else{
                value++;
            }
        }
        return value;
    }
    //Draws a card to the player's hand
    void hit(){
        playerHand.add(mainDeck.draw());
        System.out.println("After hitting, you have:");
        playerHand.printComponents();
        if (value(playerHand) > 21){
            System.out.println("You busted!");
            lose();
        }
    }
    //Allows player to double their bet and draw only one card
    void doubleDown(){
        if (money < bet){ //Ensures the user has enough to double down
            System.out.println("You don't have enough money to double down");
        }
        else{
            playerHand.add(mainDeck.draw());
            money = money - bet;
            bet = bet * 2;
            doubledDown = true; //Used to make sure they can't do anything else
        }

    }
    //For debugging/testing
    void printHands(){
        System.out.println("The dealer has:");
        dealerHand.printComponents();
        System.out.println("The player has:");
        playerHand.printComponents();
    }
    //Ends game when user wins
    void win(){
        System.out.println("You win!");
        gameOver = true;
        money = bet*2 + money;
        //informs the user what they won
        if (gambling) {
            System.out.print("You made " + bet + " dollars. You now have " + money + " dollars.");
        }
        //Resets double down, if it changed at all
        doubledDown = false;
    }
    //Ends game when user loses
    void lose(){
        System.out.println("You lose...");
        gameOver = true;
        //informs the user what they lost
        if (gambling) {
            System.out.print("You lost " + bet + " dollars. You now have " + money + " dollars.");
        }
        //Resets double down, if it changed at all
        doubledDown = false;
    }
    //Asks the user if they want to bet and if so how much
    void askBet(){
        //First determines if user wants to play with cash
        System.out.println("Are we gonna bet anything?");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        if (answer.toLowerCase().contains("y") && money > 0){
            System.out.println("Great! How much will you be betting?");
            System.out.println("You currently have " + money + " credits");
            Scanner betAmount = new Scanner(System.in);
            while (!betAmount.hasNextInt()) { //Makes sure its a number
                System.out.println("Please enter a valid number.");
                betAmount.nextLine();
            }
            int currentBet = betAmount.nextInt();
            //Makes sure they're not betting more than they have or
            //a negative number
            while (currentBet > money || currentBet <= 0){
                System.out.println("You can't bet that. Please enter a real bet");
                betAmount.nextLine();
                while (!betAmount.hasNextInt()) { //Same as before
                    System.out.println("Please enter a valid number.");
                    betAmount.nextLine();
                }
                currentBet = betAmount.nextInt();
            }
            bet = currentBet; //Assigns the value to the public var
            money -= bet; //Takes the bet out of your hand
            gambling = true;
        }
        else if (money <= 0 && answer.toLowerCase().contains("y")){
            System.out.println("Sorry, you don't have enough money to gamble");
        }
        else {
            gambling = false;
            System.out.print("Cool.");
        }
    }
}