package be.fourcolors.mvp.model.game;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;

import java.util.List;

public interface Player {
    List<Card> getCards();
    void playCard(Card card);
    void addCard(Card card);
    void uno();
    boolean isPlayerTurn();
    void setPlayerTurn(boolean playerTurn);
    int play();
    CardColor selectWildColor();
}
