package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.cards.Card;
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

    }

    private void updateView() {
        setPlayedCard();
    }

    public void addWindowEventHandlers() {

    }

    private void setPlayedCard() {
        Card playedCard = model.getPlayedCard();
        String playedCardFile = "/images/cards/normal/" + playedCard.getFileName() + ".png";
        System.out.println(playedCardFile);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(playedCardFile)));
        view.getPlayedCard().setImage(image);
    }
}
