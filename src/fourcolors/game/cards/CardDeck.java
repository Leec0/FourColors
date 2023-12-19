package fourcolors.game.cards;

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
        for (CardColor cardColor : CardColor.values()) {
            if (cardColor == CardColor.WILD) {
                for (int i = 0; i < 4; i++) {
                    cards.add(new Card(CardType.DRAW4, cardColor));
                    cards.add(new Card(CardType.CHANGE, cardColor));
                }
            } else {
                cards.add(new Card(0, cardColor));
                for (int i = 1; i <= 9; i++) {
                    cards.add(new Card(i, cardColor));
                    cards.add(new Card(i, cardColor));
                }
                for (int i = 0; i < 2; i++) {
                    cards.add(new Card(CardType.DRAW2, cardColor));
                    cards.add(new Card(CardType.REVERSE, cardColor));
                    cards.add(new Card(CardType.SKIP, cardColor));
                }
            }
        }
        Collections.shuffle(cards);
    }
}