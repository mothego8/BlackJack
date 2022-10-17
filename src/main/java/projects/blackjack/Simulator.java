package projects.blackjack;

public class Simulator {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.preGame();
        game.playGame();
    }
}
