package fourcolors;

import fourcolors.Players.HumanPlayer;

import java.util.ArrayList;

public class PlayField {
    private Card playedCard;
    private CardDeck cardDeck;
    private ArrayList<Player> players;
    private static final int AMOUNT_OF_START_CARDS = 7;

    public PlayField(int playerAmount) {
        cardDeck = new CardDeck();
        players = new ArrayList<>();
        playedCard = cardDeck.takeCard(0);
        System.out.println(playedCard);
        while (playedCard.getColor() == Color.WILD) {
            cardDeck.addCard(playedCard);
            playedCard = cardDeck.takeCard(0);
        }
        if (playerAmount < 2 || playerAmount > 4) {
            playerAmount = 2;
        }
        for (int i = 0; i < playerAmount; i++) {
            players.add(new HumanPlayer());
        }
        for (Player player : players) {
            for (int i = 0; i < AMOUNT_OF_START_CARDS; i++) {
                player.giveCard(cardDeck.takeCard(0));
            }
        }
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public void printPlayerCards() {
        for (Player player : players) {
            System.out.println(player);
            System.out.println();
        }
    }
}
