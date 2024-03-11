package be.fourcolors.mvp.model.game.players;

import be.fourcolors.mvp.model.game.Player;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final List<Card> cards;
    private boolean playerTurn;

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

    @Override
    public void uno() {

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
    public int play() {
        return 0;
    }

    @Override
    public CardColor selectWildColor() {
        return null;
    }
}
