package fourcolors.cards;

public class Card {
    private final int number;
    private final CardType type;
    private final Color color;

    public Card(CardType type, Color color) {
        this.number = -1;
        this.type = type;
        this.color = color;
    }

    public Card(int number, Color color) {
        this.number = number;
        this.type = null;
        this.color = color;
    }

    public String toString() {
        if (type == null) return color.toString() + number;
        else return color.toString() + type;
    }

    public Color getColor() {
        return color;
    }
}