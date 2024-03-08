package be.fourcolors.mvp.model.game.players;

import be.fourcolors.mvp.model.game.Player;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiEasy implements Player {
    private final List<Card> cards;
    private final Random random;
    private boolean playerTurn;

    public AiEasy() {
        cards = new ArrayList<>();
        random = new Random();
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
    }

    @Override
    public void uno() {

    }

    public CardColor selectWildColor() {
        return switch (random.nextInt(4)) {
            case 0 -> CardColor.RED;
            case 1 -> CardColor.YELLOW;
            case 2 -> CardColor.BLUE;
            case 3 -> CardColor.GREEN;
            default -> null;
        };
    }

    @Override
    public boolean isPlayerTurn() {
        return playerTurn;
    }

    @Override
    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}
