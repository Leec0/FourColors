package fourcolors.Players;

import fourcolors.Card;
import fourcolors.Player;

import java.util.ArrayList;

public class HumanPlayer implements Player {
    private final ArrayList<Card> cards;

    public HumanPlayer() {
        cards = new ArrayList<>();
    }

    @Override
    public void playCard(Card card) {

    }

    @Override
    public void giveCard(Card card) {
        cards.add(card);
    }

    @Override
    public void uno() {

    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card).append("\n");
        }
        return stringBuilder.toString();
    }
}
