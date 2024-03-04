package be.fourcolors.mvp.model.game.cards;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final int number;
    private final CardType type;
    private final CardColor cardColor;

    public Card(CardType type, CardColor cardColor) {
        this.number = -1;
        this.type = type;
        this.cardColor = cardColor;
    }

    public Card(int number, CardColor cardColor) {
        this.number = number;
        this.type = null;
        this.cardColor = cardColor;
    }

    public String toString() {
        if (type == null) return cardColor.toString() + number;
        else return cardColor.toString() + type;
    }

    public CardColor getColor() {
        return cardColor;
    }

    public int getNumber() {
        return number;
    }

    public CardType getType() {
        return type;
    }

    public String getFileName() {
        if (type != null) {
            return String.format("%s_%s", cardColor.getFileName(), type.getFileName());
        }
        return String.format("%s_%d", cardColor.getFileName(), number);
    }

    @Override
    public int compareTo(Card o) {
        if (this.cardColor.compareTo(o.cardColor) == 0) {
            if (this.type != null && o.type != null) {
                return this.type.compareTo(o.type);
            } else if (this.type == null && o.type != null) {
                return -1;
            } else if (this.type != null) {
                return 1;
            } else {
                return Integer.compare(this.number, o.number);
            }
        } else {
            return this.cardColor.compareTo(o.cardColor);
        }
    }
}
