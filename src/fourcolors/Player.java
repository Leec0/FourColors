package fourcolors;

import fourcolors.cards.Card;

import java.util.List;

public interface Player {
    public List<Card> getCards();
    public int playTurn();
    public Card playCard(int index);
    public void addCard(Card card);
    public void uno();
    public int selectWildColor();
}
