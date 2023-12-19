package fourcolors.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    private int addedCards;
    private static final int SHUFLE_CARDS = 10;

    public CardDeck() {
        cards = new ArrayList<>();
        addedCards = 0;
        fillDeck();
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card takeCard(int index) {
        if (index >= 0 && index <= cards.size()) {
            Card card;
            card = cards.get(index);
            cards.remove(index);
            return card;
        } else {
            System.err.println("Foutieve index");
            return null;
        }
    }

    public void addCard(Card card) {
        cards.add(card);
        addedCards++;
        if (addedCards >= SHUFLE_CARDS) {
            addedCards = 0;
            Collections.shuffle(cards);
        }
    }

    private void fillDeck() {
        for (Color color : Color.values()) {
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
}