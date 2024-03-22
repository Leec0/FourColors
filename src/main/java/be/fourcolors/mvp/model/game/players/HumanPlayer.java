package be.fourcolors.mvp.model.game.players;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.players.interfaces.Player;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player {
    private final List<Card> cards;
    private boolean playerTurn;
    private boolean calledOneCard;

    public HumanPlayer() {
        cards = new ArrayList<>();
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public void playCard(Card card) {
        cards.remove(card);
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        cards.sort(Card::compareTo);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    @Override
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    @Override
    public void oneCard() {
        if (canCall()) calledOneCard = true;
    }

    @Override
    public void resetOneCard() {
        calledOneCard = false;
    }

    @Override
    public boolean hasOneCard() {
        return cards.size() == 1;
    }

    @Override
    public boolean isCalledOneCard() {
        return calledOneCard;
    }

    @Override
    public boolean canCall() {
        return cards.size() <= 2;
    }
}
