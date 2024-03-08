package be.fourcolors.mvp.model.game;

import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.cards.CardDeck;
import be.fourcolors.mvp.model.game.cards.CardType;
import be.fourcolors.mvp.model.game.players.AiEasy;
import be.fourcolors.mvp.model.game.players.HumanPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    private Card playedCard;
    private CardColor wildCardColor;
    private final CardDeck cardDeck;
    private final List<Player> players;
    private static final int AMOUNT_OF_START_CARDS = 7;
    private boolean clockWise;
    private int playerTurn;

    public PlayField() {
        cardDeck = new CardDeck();
        players = new ArrayList<>();
        setStartCard();
        int playerAmount = 2;
        players.add(new HumanPlayer());
        for (int i = 0; i < playerAmount - 1; i++) {
            players.add(new AiEasy());
        }
        givePlayerCards();
        players.get(0).setPlayerTurn(true);
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
        boolean result;
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

    private void Draw(Player player, int amount) {
        Card givenCard;
        for (int i = 0; i < amount + 1; i++) {
            givenCard = cardDeck.takeCard(0);
            player.addCard(givenCard);
        }
    }

    public void setWildColor(CardColor cardColor) {
        wildCardColor = cardColor;
    }

    public boolean gameEnd() {
        for (Player player : players) {
            if (player.getCards().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void playerDraw(Player player) {
        Card drawedCard;
        boolean emptyDeck;
        do {
            emptyDeck = cardDeck.getSize() <= 0;
            if (emptyDeck) return;
            drawedCard = cardDeck.takeCard(0);
            player.addCard(drawedCard);
        } while (!canBePlayed(drawedCard));
        playerTurn = nextPlayer();
    }

    public void playerPlay(Player player, Card playerPlayedCard) {
        if (canBePlayed(playerPlayedCard)) {
            cardDeck.addCard(playedCard);
            playedCard = playerPlayedCard;
            player.playCard(playerPlayedCard);
            if (playerPlayedCard.getType() == CardType.REVERSE) {
                clockWise = !clockWise;
                if (players.size() == 2) {
                    playerTurn = nextPlayer();
                }
            } else if (playerPlayedCard.getType() == CardType.SKIP) {
                playerTurn = nextPlayer();
            } else if (playerPlayedCard.getType() == CardType.DRAW) {
                playerTurn = nextPlayer();
                if (playerPlayedCard.getColor() == CardColor.WILD) {
                    Draw(players.get(playerTurn), 4);
                }
                else {
                    Draw(players.get(playerTurn), 2);
                }
            }
            playerTurn = nextPlayer();
        }
    }

    private int nextPlayer() {
        int playerTurn = this.playerTurn;
        players.get(playerTurn).setPlayerTurn(false);
        if (clockWise) {
            playerTurn++;
            if (playerTurn >= players.size()) {
                playerTurn = 0;
            }
        } else {
            playerTurn--;
            if (playerTurn < 0) {
                playerTurn = players.size() - 1;
            }
        }
        players.get(playerTurn).setPlayerTurn(true);
        return playerTurn;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public int getWinningPlayer() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCards().isEmpty()) {
                return i + 1;
            }
        }
        return 0;
    }
}
