package fourcolors;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private ArrayList<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
        Color[] colors = Color.values();
        for (Color color : colors) {
            if (color == Color.WILD) {
                for (int i = 0; i < 4; i++) {
                    cards.add(new Card(CardType.DRAW4, color));
                    cards.add(new Card(CardType.CHANGE, color));
                }
            } else {
                cards.add(new Card(0, color));
                for (int i = 1; i <= 9; i++) {
                    cards.add(new Card(i, color));
                    cards.add(new Card(i, color));
                }
                for (int i = 0; i < 2; i++) {
                    cards.add(new Card(CardType.DRAW2, color));
                    cards.add(new Card(CardType.REVERSE, color));
                    cards.add(new Card(CardType.SKIP, color));
                }
            }
        }
        Collections.shuffle(cards);
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}