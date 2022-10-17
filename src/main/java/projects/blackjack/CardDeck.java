package projects.blackjack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CardDeck{
    private int numOfDecks;
    private int counter = 1;
    private int[] shuffledDeck;

    public CardDeck(int numOfDecks){
        this.numOfDecks = numOfDecks;
        this.createShoe();
    }
    
    public int dealCards(){
        int dealtCard = shuffledDeck[counter];
        counter++;
        return dealtCard;
    }

    public void createShoe(){
        // create and shuffle 8 decks
        int[] shuffledShoe = new int[52 * this.numOfDecks];
        for (int i = 0; i < shuffledShoe.length; i++) {
            if(i % 13 == 0) shuffledShoe[i] = 11;
            else shuffledShoe[i] = Math.min(i % 13, 9) + 1;
        }
        Random rnd = ThreadLocalRandom.current();
        for (int i = shuffledShoe.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = shuffledShoe[index];
            shuffledShoe[index] = shuffledShoe[i];
            shuffledShoe[i] = a;
        }
        this.shuffledDeck = shuffledShoe;
    }
}

