package be.fourcolors.mvp.model.game.cards;

public class Card {
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
}