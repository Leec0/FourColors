package fourcolors;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private final ArrayList<Card> cards;
    private int addedCards;
    private static final int MAX_DECK_SIZE = 108;

    public CardDeck() {
        cards = new ArrayList<>();
        addedCards = 0;
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
        if (addedCards >= MAX_DECK_SIZE/2) {
            addedCards = 0;
            Collections.shuffle(cards);
        }
    }
}