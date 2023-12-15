package fourcolors;

import fourcolors.cards.Card;
import fourcolors.cards.CardDeck;
import fourcolors.cards.Color;
import fourcolors.players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    private Card playedCard;
    private final CardDeck cardDeck;
    private final List<Player> players;
    private static final int AMOUNT_OF_START_CARDS = 7;

    public PlayField(int playerAmount) {
        cardDeck = new CardDeck();
        players = new ArrayList<>();
        setStartCard();
        if (playerAmount < 2 || playerAmount > 4) {
            playerAmount = 2;
        }
        for (int i = 0; i < playerAmount; i++) {
            players.add(new HumanPlayer());
        }
        givePlayerCards();
    }

    public void playGame() {
        do {
            for (Player player : players) {
                int playOption = player.playTurn();
                if (playOption == player.getCards().size() + 1) {
                    do {
                        Card drawedCard = cardDeck.takeCard(0);
                        player.giveCard(drawedCard);
                    } while ();
                } else {
                    cardDeck.addCard(playedCard);
                    playedCard = player.playCard(player.getCards().get(playOption - 1));
                }
            }
        } while (true);
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

    private void setStartCard() {
        playedCard = cardDeck.takeCard(0);
        while (playedCard.getColor() == Color.WILD) {
            cardDeck.addCard(playedCard);
            playedCard = cardDeck.takeCard(0);
        }
    }

    private void givePlayerCards() {
        for (Player player : players) {
            for (int i = 0; i < AMOUNT_OF_START_CARDS; i++) {
                player.giveCard(cardDeck.takeCard(0));
            }
        }
    }

    private boolean canBePlayed() {
        boolean result;
    }
}
