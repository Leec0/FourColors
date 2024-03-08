package be.fourcolors.mvp.model.game;

import be.fourcolors.mvp.model.game.cards.Card;

import java.util.List;

public interface Player {
    public List<Card> getCards();
    public void playCard(Card card);
    public void addCard(Card card);
    public void uno();
    public boolean isPlayerTurn();
    public void setPlayerTurn(boolean playerTurn);
}
