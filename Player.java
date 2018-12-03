import java.util.*;

public class Player {
    Deck hand = new Deck(false);
    String username = "Player";
    public boolean onTurn = true;
    public boolean gambling = false;
    public boolean doubledDown = false;
    public boolean splitted = false;
    public boolean hasBlackjack = false;
    public int money = 50;
    public int bet = 0;

    Player(){
    }
    //Shows what the player has
    void printHand(){
        System.out.println("You have: " + value()); //includes sum for ez reference
        hand.printComponents();
    }

    void addCard(Card c){
        hand.add(c);
    }
    //Allows for resetting all the game-specific vars at the end
    void reset(){
        onTurn = true;
        gambling = false;
        doubledDown = false;
        splitted = false;
        hasBlackjack = false;
        hand.clear();
    }
    //ALEts player draw a card
    void hit(){
        hand.add(Blackjack.mainDeck.draw());
        System.out.println("After hitting, you have: " + value());
        hand.printComponents();
        if (value() > 21){
            System.out.println(username + " busted!");
            lose();
        }
    }

    //Allows player to double their bet and draw only one card
    void doubleDown(){
        if (gambling){
            if (money < bet){ //Ensures the user has enough to double down
                System.out.println("You don't have enough money to double down");
            }
            else{
                money = money - bet;
                bet = bet * 2;
                System.out.println("Doubling your bet to " + bet + " credits");
                doubledDown = true; //Used to make sure they can't do anything else
                hit();
            }
        }
        //Error statment, ideally this won't show up at all
        else {
            System.out.println("Since you're not betting, this act has literally no benefits as opposed to hitting");
        }
    }

    //Had to remove the split function, difficulty in making it function

    /*
    //Lets the player split their deck in two and essentially function as 2 players
    void split(){
    //Ensures they have enough money to split and they're allowed to
    if (((gambling && money >= bet) || (gambling == false)) && hand.inDeck.get(0).rankValue == hand.inDeck.get(1).rankValue){
    money = money - bet;
    //Creates new player object that's controlled by the user
    Player pSplit = new Player();
    pSplit.hand = hand.cut(); //Takes the current deck and splits it
    //Adds a single card to both hands
    hand.add(Blackjack.mainDeck.draw());
    pSplit.hand.add(Blackjack.mainDeck.draw());
    System.out.println("In your first deck:");
    printHand();
    System.out.println("In your second deck:");
    pSplit.printHand();
    //Used to stop double-splitting and as a flag to stop the dealer
    //reveal until after both decks are done
    splitted = true;
    //pSplit.splitted = true;
    pSplit.bet = bet;
    System.out.println("Let's start with your first hand");
    playersTurn();
    System.out.println("Okay, now for the second hand.");
    pSplit.playersTurn();
    }
    else {
    System.out.println("You can't split in this case.");
    }
    }
     */

    //calculates and returns the value of the player's hand
    int value(){
        int value = 0;
        byte aces = 0;
        for (Card currentCard: hand.inDeck){
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
    //Ends game when user loses
    void lose(){
        System.out.println(username + " lost...");
        //informs the user what they lost
        if (gambling) {
            System.out.println(username + " lost " + bet + " dollars. You now have " + money + " dollars.");
        }
    }
    //ends game when user wins
    void win(){
        System.out.println(username + " won!");
        money = bet*2 + money;
        //informs the user what they won
        if (gambling) {
            System.out.println(username + " made " + bet + " dollars. They now have " + money + " dollars.");
        }
    }
    //Ends game when user ties
    void push(){
        System.out.println(username + " tied.");
        money = bet + money;
        //informs the user what they won
        if (gambling) {
            System.out.println(username + " money stays the same at " + money + " credits");
        }
    }
    //Asks the user if they want to bet and if so how much
    void askBet(){
        //First determines if user wants to play with cash
        System.out.println(username + ", are we gonna bet anything? Y/N");
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
            System.out.println("Cool, no betting here.");
        }
    }

    public void playersTurn(){
        System.out.println(username + ", your turn.");
        printHand();
        int move = 0;
        //Doesn't go through the turn if the player already won
        if (hasBlackjack){
            System.out.println("You got blackjack");
            win();
        }
        else{
            while (onTurn) {
                move ++;
                String choice;
                boolean choiceMade = false;

                System.out.print("Right now, you can: hit, stay");
                /*
                if (!splitted && move == 1 && bet <= money && hand.inDeck.get(0).rankValue == hand.inDeck.get(1).rankValue) {
                System.out.print(", split");
                }
                 */
                //ensures the user has a reason to double down before letting them
                if (!doubledDown && gambling && move == 1 && bet <= money) {
                    System.out.print(", double down");
                }
                System.out.println("");
                Scanner input = new Scanner(System.in);
                while (!choiceMade) {
                    //Makes a pseudo until loop, won't go again unless there's an issue
                    choiceMade = true; 
                    choice = input.nextLine();
                    if (choice.equalsIgnoreCase("hit")) {
                        hit();
                        if (value() > 21) {
                            onTurn = false;
                        }
                    } else if (choice.equalsIgnoreCase("stay")) {
                        onTurn = false;
                        /*
                        } else if (!splitted && choice.equalsIgnoreCase("split") && move == 1 && hand.inDeck.get(0).rankValue == hand.inDeck.get(1).rankValue) {
                        split();
                        onTurn = false;
                         */
                    } else if ((!doubledDown && gambling) && bet<= money && choice.equalsIgnoreCase("double down")) {
                        doubleDown();
                        onTurn = false;
                    } else { //Incorrect user input, repeats the loop
                        choiceMade = false;
                        System.out.println("Please enter one of the listed commands.");
                    }
                }
            }
        }
    }
}