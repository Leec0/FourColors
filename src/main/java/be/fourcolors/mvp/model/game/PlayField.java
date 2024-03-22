package be.fourcolors.mvp.model.game;

import be.fourcolors.mvp.model.checks.CardChecker;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.cards.CardDeck;
import be.fourcolors.mvp.model.game.cards.CardType;
import be.fourcolors.mvp.model.game.players.AiEasy;
import be.fourcolors.mvp.model.game.players.interfaces.BotBase;
import be.fourcolors.mvp.model.game.players.HumanPlayer;
import be.fourcolors.mvp.model.game.players.interfaces.Player;

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
    private final CardChecker cardChecker;
    private int currentDrawAmount;

    public PlayField() {
        cardDeck = new CardDeck();
        players = new ArrayList<>();
        cardChecker = new CardChecker();
        currentDrawAmount = 0;
        setStartCard();
        int playerAmount = 2;
        players.add(new HumanPlayer());
        for (int i = 0; i < playerAmount - 1; i++) {
            players.add(new AiEasy(cardChecker));
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

    private void draw(Player player, int amount) {
        Card givenCard;
        for (int i = 0; i < amount; i++) {
            givenCard = cardDeck.takeCard(0);
            player.addCard(givenCard);
        }
    }

    private void resetOneCard() {
        for (Player player : players) {
            if (!player.hasOneCard()) {
                player.resetOneCard();
            }
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
        if (!gameEnd()) {
            if (!cardChecker.isRequiredToPlayDraw()) {
                Card drawedCard;
                boolean emptyDeck;
                do {
                    emptyDeck = cardDeck.getSize() <= 0;
                    if (emptyDeck) return;
                    drawedCard = cardDeck.takeCard(0);
                    player.addCard(drawedCard);
                } while (!cardChecker.canBePlayed(drawedCard, playedCard, wildCardColor));
            } else {
                draw(player, currentDrawAmount);
                currentDrawAmount = 0;
                cardChecker.setRequiredToPlayDraw(false);
            }
            playerTurn = nextPlayer();
            resetOneCard();
        }
    }

    public void playerPlay(Player player, Card playerPlayedCard) {
        if (!gameEnd()) {
            if (cardChecker.canBePlayed(playerPlayedCard, playedCard, wildCardColor)) {
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
                    if (playerPlayedCard.getColor() == CardColor.WILD) {
                        currentDrawAmount += 4;
                    } else {
                        currentDrawAmount += 2;
                    }
                    if (playerHasCardOfTypeDraw(players.get(nextPlayer()))) {
                        cardChecker.setRequiredToPlayDraw(true);
                    } else {
                        cardChecker.setRequiredToPlayDraw(false);
                        playerTurn = nextPlayer();
                        draw(players.get(playerTurn), currentDrawAmount);
                        currentDrawAmount = 0;
                    }
                }
                playerTurn = nextPlayer();
                resetOneCard();
            }
        }
    }

    private boolean playerHasCardOfTypeDraw(Player player) {
        for (Card card : player.getCards()) {
            if (card.getType() == CardType.DRAW) return true;
        }
        return false;
    }

    public void botsPlay() {
        if (players.get(playerTurn) instanceof BotBase player) {
            if (playerWithOneCard()) {
                if (player.callOutPlayers()) callOutPlayers();
            }
            if (player.playOrDraw(playedCard, wildCardColor)) {
                Card playerPlayedCard = player.play(playedCard, wildCardColor);
                if (playerPlayedCard.getColor() == CardColor.WILD) {
                    setWildColor(player.selectWildColor());
                }
                playerPlay(player, playerPlayedCard);
            } else {
                playerDraw(player);
            }
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

    public boolean playerWithOneCard() {
        for (Player player : players) {
            if (player.hasOneCard()) return true;
        }
        return false;
    }

    public void callOutPlayers() {
        for (Player player : players) {
            if (player.hasOneCard() && !player.isCalledOneCard()) {
                draw(player, 2);
            }
        }
    }

    public CardColor getWildCardColor() {
        return wildCardColor;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
