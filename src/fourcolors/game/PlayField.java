package fourcolors.game;

import fourcolors.game.cards.Card;
import fourcolors.game.cards.CardDeck;
import fourcolors.game.cards.CardColor;
import fourcolors.game.cards.CardType;
import fourcolors.game.players.AiEasy;
import fourcolors.game.players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    private Card playedCard;
    private CardColor wildCardColor;
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
                boolean turnEnd;
                do {
                    System.out.println(playedCard);
                    if (playedCard.getColor() == CardColor.WILD) {
                        System.out.println(wildCardColor);
                    }
                    int playOption = player.playTurn();
                    if (playOption == player.getCards().size() + 1) {
                        turnEnd = playerDraw(player);
                    } else {
                        turnEnd = playerPlay(player, playOption - 1);
                    }
                } while (!turnEnd);
            }
        } while (!gameEnd());
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
        while (playedCard.getColor() == CardColor.WILD) {
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
        result = card.getColor() == CardColor.WILD;
        if (!result) {
            result = card.getColor() == playedCard.getColor();
            if (!result) {
                if (card.getType() == null) {
                    result = card.getNumber() == playedCard.getNumber();
                } else {
                    result = card.getType() == playedCard.getType();
                }
                if (playedCard.getColor() == CardColor.WILD && !result) {
                    result = card.getColor() == wildCardColor;
                }
            }
        }
        return result;
    }

    private void Draw(Player player) {
        if (playedCard.getType() == CardType.DRAW4) {
            Card givenCard;
            for (int i = 0; i < 4; i++) {
                givenCard = cardDeck.takeCard(0);
                player.addCard(givenCard);
            }
        }
        if (playedCard.getType() == CardType.DRAW2) {
            Card givenCard;
            for (int i = 0; i < 2; i++) {
                givenCard = cardDeck.takeCard(0);
                player.addCard(givenCard);
            }
        }
    }

    private void setWildColor(Player player) {
        switch (player.selectWildColor()) {
            case 1:
                wildCardColor = CardColor.RED;
                break;
            case 2:
                wildCardColor = CardColor.BLUE;
                break;
            case 3:
                wildCardColor = CardColor.YELLOW;
                break;
            case 4:
                wildCardColor = CardColor.GREEN;
                break;
        }
    }

    private boolean gameEnd() {
        for (Player player : players) {
            if (player.getCards().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean playerDraw(Player player) {
        Card drawedCard;
        boolean emptyDeck;
        do {
            drawedCard = cardDeck.takeCard(0);
            player.addCard(drawedCard);
            emptyDeck = cardDeck.getSize() <= 0;
        } while (!canBePlayed(drawedCard) && !emptyDeck);
        return true;
    }

    private boolean playerPlay(Player player, int cardIndex) {
        Card playerPlayedCard = player.getCards().get(cardIndex);
        if (canBePlayed(playerPlayedCard)) {
            cardDeck.addCard(playedCard);
            playedCard = player.playCard(cardIndex);
            if (playerPlayedCard.getColor() == CardColor.WILD) {
                setWildColor(player);
            }
            return true;
        } else {
            System.out.println("Deze kaart kan niet gespeeld worden.");
            return false;
        }
    }
}
