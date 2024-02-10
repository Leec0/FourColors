package be.fourcolors.mvp.model.game;

import be.fourcolors.mvp.model.game.cards.Card;

import java.util.List;

public interface Player {
    public List<Card> getCards();
    public int playTurn();
    public Card playCard(int index);
    public void addCard(Card card);
    public void uno();
    public int selectWildColor();
}
