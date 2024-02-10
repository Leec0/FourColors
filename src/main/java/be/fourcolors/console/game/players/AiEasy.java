package be.fourcolors.console.game.players;

import be.fourcolors.console.game.Player;
import be.fourcolors.console.game.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiEasy implements Player {
    private final List<Card> cards;
    private final Random random;

    public AiEasy() {
        cards = new ArrayList<>();
        random = new Random();
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int playTurn() {
        return random.nextInt(cards.size() + 1) + 1;
    }

    @Override
    public Card playCard(int index) {
        Card returnCard = cards.get(index);
        cards.remove(index);
        return returnCard;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public void uno() {

    }

    @Override
    public int selectWildColor() {
        return random.nextInt(4) + 1;
    }
}
