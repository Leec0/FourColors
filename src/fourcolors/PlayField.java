package fourcolors;

import fourcolors.cards.Card;
import fourcolors.cards.CardDeck;
import fourcolors.cards.Color;
import fourcolors.players.AiEasy;
import fourcolors.players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayField {
    private Card playedCard;
    private Color wildColor;
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
        players.add(new HumanPlayer());
        for (int i = 0; i < playerAmount - 1; i++) {
            players.add(new AiEasy());
        }
        givePlayerCards();
    }

    public void playGame() {
        do {
            for (Player player : players) {
                boolean turnEnd = false;
                do {
                    int playOption = player.playTurn();
                    if (playOption == player.getCards().size() + 1) {
                        Card drawedCard;
                        boolean emptyDeck = false;
                        do {
                            drawedCard = cardDeck.takeCard(0);
                            player.addCard(drawedCard);
                            emptyDeck = cardDeck.getSize() <= 0;
                        } while (!canBePlayed(drawedCard) && !emptyDeck);
                        turnEnd = true;
                    } else {
                        Card playerPlayedCard = player.getCards().get(playOption - 1);
                        if (canBePlayed(playerPlayedCard)) {
                            cardDeck.addCard(playedCard);
                            playedCard = player.playCard(playOption - 1);
                            turnEnd = true;
                            if (playerPlayedCard.getColor() == Color.WILD) {
                                setWildColor(player);
                            }
                        } else {
                            System.out.println("Deze kaart kan niet gespeeld worden.");
                        }
                    }
                } while (!turnEnd);
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
                player.addCard(cardDeck.takeCard(0));
            }
        }
    }

    private boolean canBePlayed(Card card) {
        boolean result = false;
        result = card.getColor() == Color.WILD;
        if (!result) {
            result = card.getColor() == playedCard.getColor();
            if (!result) {
                if (card.getType() == null) {
                    result = card.getNumber() == playedCard.getNumber();
                } else {
                    result = card.getType() == playedCard.getType();
                }
                if (playedCard.getColor() == Color.WILD && !result) {
                    result = card.getColor() == wildColor;
                }
            }
        }
        return result;
    }

    private void setWildColor(Player player) {
        switch (player.selectWildColor()) {
            case 1:
                wildColor = Color.RED;
                break;
            case 2:
                wildColor = Color.BLUE;
                break;
            case 3:
                wildColor = Color.YELLOW;
                break;
            case 4:
                wildColor = Color.GREEN;
                break;
        }
    }
}
