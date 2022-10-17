package projects.blackjack;
//should have running double of money to play with
//should have integer of total number of his cards combined
//count of times won

public class User {
    private double cashToPlay;
    private int currentHand;
    private int timesWon;

    public void addCardToHand(int cardValue){
        this.currentHand += cardValue;
    }
    
    public int getCurrentHand(){
        return this.currentHand;
    }

    public void setCash(double cash){
        this.cashToPlay = cash;
    }

    public void addCashAfterWin(double cash){
        this.cashToPlay += cash;
    }

    public double getCurrentCash(){
        return this.cashToPlay;
    }

    public void addWin(){
        this.timesWon +=1;
    }

    public int getWins(){
        return this.timesWon;
    }

    public void removeCardsFromHand(){
        this.currentHand = 0;
    }
}
