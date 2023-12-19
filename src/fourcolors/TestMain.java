package fourcolors;

import fourcolors.game.PlayField;

public class TestMain {
    public static void main(String[] args) {
        PlayField playField = new PlayField(2);
        playField.printPlayerCards();
        System.out.println(playField.getPlayedCard());
        playField.playGame();
    }
}