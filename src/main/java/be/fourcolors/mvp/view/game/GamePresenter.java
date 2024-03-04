package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.Player;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.players.HumanPlayer;
import javafx.scene.image.Image;

import java.util.Objects;

public class GamePresenter {
    private final GameView view;
    private final PlayField model;

    public GamePresenter(GameView view, PlayField model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getCardPile().setOnAction(actionEvent -> {

        });
    }

    private void updateView() {
        setPlayedCard();
        setPlayerCards();
    }

    public void addWindowEventHandlers() {

    }

    private void setPlayedCard() {
        Card playedCard = model.getPlayedCard();
        String playedCardFile = "/images/cards/normal/" + playedCard.getFileName() + ".png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(playedCardFile)));
        view.getPlayedCard().setImage(image);
    }


    private void setPlayerCards() {
        for (Player player : model.getPlayers()) {
            if (player.getClass().equals(HumanPlayer.class)) {
                for (Card card : player.getCards()) {
                    view.addButton(card);
                }
            }
        }
    }
}
