package fourcolors;

import java.util.ArrayList;

public class CardDeck {
    private ArrayList<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Color color;
            switch (i) {
                case 0 -> color = Color.BLUE;
                case 1 -> color = Color.GREEN;
                case 2 -> color = Color.RED;
                case 3 -> color = Color.YELLOW;
                case 4 -> color = Color.WILD;
            }
            if (color == Color.WILD) {

            }
        }
    }
}
