package fourcolors;

import fourcolors.cards.Card;

import java.util.List;

public interface Player {
    public List<Card> getCards();
    public int playTurn();
    public void playCard(Card card);
    public void giveCard(Card card);
    public void uno();
}
