package fourcolors;

public class TestMain {
    public static void main(String[] args) {
        PlayField playField = new PlayField(4);
        playField.printPlayerCards();
        System.out.println(playField.getPlayedCard());
        playField.playGame();
    }
}