package projects.blackjack;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner userInput = new Scanner(System.in);
    User user = new User();
    Dealer dealer = new Dealer();
    CardDeck cards = new CardDeck(8);
    int delay = 1000;

    public void preGame() throws IOException{
        boolean end = false;
		while (!end) {
            try {
				System.out.print("\nEnter how much you wish to deposit: ");
                double cashDeposited = userInput.nextDouble();
                user.setCash(cashDeposited);
                end = true;
            }catch (InputMismatchException e) {
                System.out.println("\nInvalid Character(s). Only Numbers.");
            }
        }
    }
    
    public void playGame() throws IOException, InterruptedException{
        user.removeCardsFromHand();
        dealer.removeCardsFromHand();
        boolean end = false;
        double cashToPlay;
		while (!end) {
			System.out.print("\nEnter how much you wish to wager: ");
            Thread.sleep(delay);
			cashToPlay = userInput.nextDouble();
            if(cashToPlay > user.getCurrentCash()){
                double cashNeeded = cashToPlay - user.getCurrentCash();
                System.out.println("Must deposit $" + cashNeeded + " to continue: ");
                System.out.println("If you would like to play press 1 and deposit neccessary amount, if you would like to exit game press 2: ");
                System.out.print("Selection: ");
                int selection = userInput.nextInt();
                switch (selection) {
                    case 1:    
                        try {
                            double cashDeposited = userInput.nextDouble();
                            user.setCash(cashDeposited += user.getCurrentCash());
                        }catch (InputMismatchException e) {
                            System.out.println("\nInvalid Character(s). Only Numbers.");
                        }
                        break;
                    case 2:
                        exitGame();
                        break;
                    default:
                    System.out.println("\nInvalid Choice."); 
                }  
            }
            dealer.addCardToHand(cards.dealCards());
            System.out.println("\nDealer's show card: " + dealer.getCurrentHand());
            Thread.sleep(delay);
            int dealerHideCard = cards.dealCards();
            if(dealer.getCurrentHand() == 21){
                lose(cashToPlay);
                return;
            }
            int firstCard = cards.dealCards();
            user.addCardToHand(firstCard);
            System.out.println("\nyour first card is: " + firstCard);
            Thread.sleep(delay);
            int secondCard = cards.dealCards();
            System.out.println("\nyour second card is: " + secondCard);
            user.addCardToHand(secondCard);
            Thread.sleep(delay);
            System.out.println("\nYour initial hand: " + user.getCurrentHand());
            Thread.sleep(delay);
            if(user.getCurrentHand() == 21){
                System.out.println("BlackJack!!!");
                win(cashToPlay * 1.5);
            }
            System.out.println("\nPlease Choose Your Next Move:");
            Thread.sleep(300);
            System.out.println("Press 1 to Hit");
            Thread.sleep(300);
            System.out.println("Press 2 to Stay");
            Thread.sleep(300);
            System.out.print("\nSelection: ");
            Thread.sleep(delay);
            //System.out.println("Type 3 - Split");
            //System.out.println("Type 4 - Double Down");
            int selection = userInput.nextInt();
            switch (selection) {
                case 1:
                    boolean hit = false;
                    while(!hit){
                        user.addCardToHand(cards.dealCards());
                        System.out.println("\nYour current hand: " + user.getCurrentHand());
                        if(user.getCurrentHand() > 21){
                            bust(cashToPlay);
                            return;
                        }else if(user.getCurrentHand() == 21){
                            break;
                        }else{
                            System.out.println("\nWould you like to hit again?");
                            System.out.println("Press 1 to hit");
                            System.out.println("Press 2 to stay");
                            System.out.print("\nSelection: ");
                            int selection2 = userInput.nextInt();
                            switch(selection2){
                                case 1:
                                    break;
                                case 2:
                                    hit = true;
                                    break;
                                default:
                                    System.out.println("\nInvalid Choice.");
                            }
                        }
                    }
                    break;         
                case 2:
                    break;
                /*case 3:
                    split()*/
                default:
                    System.out.println("\nInvalid Choice.");
            }
            dealer.addCardToHand(dealerHideCard);
            System.out.println("\nDealers Hand: " + dealer.getCurrentHand());
            while(dealer.getCurrentHand() < 17){
                dealer.addCardToHand(cards.dealCards());
                System.out.println("Dealers Hand: " + dealer.getCurrentHand());
                if(dealer.getCurrentHand() > 21){
                    win(cashToPlay);
                    return;
                }
            }
            if(dealer.getCurrentHand() < user.getCurrentHand()){
                win(cashToPlay);
            }else{
                lose(cashToPlay);
            }
            return;
        }
    }

    public void exitGame(){
        System.out.println("\nThank You For Playing, Come Back Anytime :)");
        System.out.println("Your final balance: $" + user.getCurrentCash());
    }

    public void win(double cash){
        System.out.println("\nYou won!!");
        user.addCashAfterWin(cash);
        print();
    }

    private void print() {
        System.out.println("Current balance: $" + user.getCurrentCash());
        System.out.println("\nIf you would like to play again press 1, if you would like to exit press 2");
        System.out.print("\nSelection: ");
        int selection2 = userInput.nextInt();
        switch (selection2) {
            case 1:
                try {
                    playGame();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case 2:
                exitGame();
                break;
            default:
                System.out.println("\nInvalid Choice.");
        }
    }

    public void lose(double cash){
        System.out.println("\nSorry you lost to the dealer :(");
        user.setCash(user.getCurrentCash() - cash);
        print();
    }

    public void bust(double cash){
        System.out.println("\nSorry you busted :(");
        user.setCash(user.getCurrentCash() - cash);
        print();
    }

    /*public Map<Integer, Integer> split(int firstCard, int secondCard){
        Map<Integer, Integer> splitHand = new HashMap<Integer, Integer>();
        splitHand.put(1, firstCard);
        splitHand.put(2, secondCard);
        System.out.println("deal another card to first hand");
        splitHand.put(1,cards.dealCards());
        System.out.println("if you would like to hit again press 1 if not press 2");

    }*/
}


