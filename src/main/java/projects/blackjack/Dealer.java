package projects.blackjack;//integer count of current hand
//count of times won

public class Dealer {
    private int currentHand;
    private int timesWon;

    public void addCardToHand(int cardValue){
        this.currentHand += cardValue;
    }
    
    public int getCurrentHand(){
        return this.currentHand;
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
