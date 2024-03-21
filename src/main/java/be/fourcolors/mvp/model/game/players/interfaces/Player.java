package be.fourcolors.mvp.model.game.players.interfaces;

import be.fourcolors.mvp.model.game.cards.Card;

import java.util.List;

public interface Player {
    List<Card> getCards();
    void playCard(Card card);
    void addCard(Card card);
    void oneCard();
    boolean isPlayerTurn();
    void setPlayerTurn(boolean playerTurn);
    void resetOneCard();
    boolean hasOneCard();
    boolean isCalledOneCard();
}
