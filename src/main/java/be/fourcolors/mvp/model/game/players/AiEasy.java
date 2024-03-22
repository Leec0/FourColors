package be.fourcolors.mvp.model.game.players;

import be.fourcolors.mvp.model.checks.CardChecker;
import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.players.interfaces.BotBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiEasy implements BotBase {
    private final List<Card> cards;
    private final Random random;
    private boolean playerTurn;
    private final CardChecker cardChecker;
    private boolean calledOneCard;

    public AiEasy(CardChecker cardChecker) {
        cards = new ArrayList<>();
        random = new Random();
        this.cardChecker = cardChecker;
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void oneCard() {
        if (hasOneCard()) calledOneCard = true;
    }

    @Override
    public boolean callOutPlayers() {
        return random.nextBoolean();
    }

    @Override
    public boolean playOrDraw(Card playedCard, CardColor wildCardColor) {
        return hasPlayableCard(playedCard, wildCardColor);
    }

    @Override
    public boolean hasPlayableCard(Card playedCard, CardColor wildCardColor) {
        for (Card card : cards) {
            if(cardChecker.canBePlayed(card, playedCard, wildCardColor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Card play(Card playedCard, CardColor wildCardColor) {
        if (random.nextBoolean()) oneCard();
        while (true) {
            Card card = cards.get(random.nextInt(cards.size()));
            if (cardChecker.canBePlayed(card, playedCard, wildCardColor)) {
                return card;
            }
        }
    }

    @Override
    public void resetOneCard() {
        calledOneCard = false;
    }

    @Override
    public boolean hasOneCard() {
        return cards.size() == 1;
    }

    public boolean isCalledOneCard() {
        return calledOneCard;
    }

    @Override
    public boolean canCall() {
        return cards.size() <= 2;
    }
}
