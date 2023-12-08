package fourcolors;

public class Card {
    private int number;
    private CardType type;

    public Card(CardType type) {
        this.type = type;
    }

    public Card(int number, CardType type) {
        this.number = number;
        this.type = type;
    }
}
//