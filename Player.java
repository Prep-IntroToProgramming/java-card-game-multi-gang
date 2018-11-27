import java.util.*;

public class Player {
    Deck hand = new Deck(false);
    String username = "Player";

    public static boolean gambling = false;
    public boolean doubledDown = false;
    public int money = 50;
    public int bet = 0;

    Player(){
        money = 50;
    }

    void printHand(){
        System.out.println("You have:");
        hand.printComponents();
    }

    void addCard(Card c){
        hand.add(c);
    }

    void hit(){
        hand.add(Blackjack.mainDeck.draw());
        System.out.println("After hitting, you have:");
        hand.printComponents();
        if (value() > 21){
            System.out.println("You busted!");
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

    int value(){
        int value = 0;
        int aces = 0;
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
        System.out.println("You lose...");
        Blackjack.gameOver = true;
        //informs the user what they lost
        if (gambling) {
            System.out.println("You lost " + bet + " dollars. You now have " + money + " dollars.");
        }
        //Resets double down, if it changed at all
        doubledDown = false;
    }
    //ends game when user wins
    void win(){
        System.out.println("You win!");
        Blackjack.gameOver = true;
        money = bet*2 + money;
        //informs the user what they won
        if (gambling) {
            System.out.println("You made " + bet + " dollars. You now have " + money + " dollars.");
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
            System.out.println("Cool, no betting here.");
        }
    }

    void playersTurn(){
        if (Blackjack.dealerBlackjack){
            lose();
        }
        //Sees if player has blackjack
        else if (value() == 21){
            System.out.println("The player has:");
            hand.printComponents();
            System.out.println("You got blackjack!");
            win();
        }
        //If no one has blackjack, game goes as normal
        else {

        }
    }
}
