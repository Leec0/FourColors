package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.cards.Card;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

import java.util.*;

public class GameView extends BorderPane {

    private HBox cardsPlayer;
    private Map<Card, Button> buttonsPlayer;
    private HBox centerBox;
    private Button cardPile;
    private Button oneCardButton;
    private ImageView playedCard;


    public GameView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        cardsPlayer = new HBox();
        buttonsPlayer = new HashMap<>();

        centerBox = new HBox();
        cardPile = new Button();
        oneCardButton = new Button("UNO");
        playedCard = new ImageView();
    }

    private void layoutNodes() {
        centerBox.setAlignment(Pos.CENTER);
        HBox.setMargin(playedCard, new Insets(0, 50, 0, 50));
        centerBox.getChildren().add(cardPile);
        centerBox.getChildren().add(playedCard);
        centerBox.getChildren().add(oneCardButton);

        playedCard.setPreserveRatio(true);
        playedCard.setFitWidth(200);
        Image deckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cards/normal/deck.png")));
        ImageView deckView = new ImageView(deckImage);
        deckView.setPreserveRatio(true);
        deckView.setFitWidth(200);
        cardPile.setGraphic(deckView);

        cardsPlayer.setAlignment(Pos.CENTER);
        setMargin(cardsPlayer, new Insets(20));

        setBottom(cardsPlayer);
        setCenter(centerBox);
    }

    public void addButtons(List<Card> cards) {
        cardsPlayer.getChildren().clear();
        for (Card card : cards) {
            Button button = new Button();
            String cardFile = "/images/cards/normal/" + card.getFileName() + ".png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cardFile)));
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(75);
            button.setGraphic(imageView);
            buttonsPlayer.put(card, button);
            cardsPlayer.getChildren().add(buttonsPlayer.get(card));
        }
    }

    public Button getCardPile() {
        return cardPile;
    }

    public Button getOneCardButton() {
        return oneCardButton;
    }

    public ImageView getPlayedCard() {
        return playedCard;
    }

    public Map<Card, Button> getButtonsPlayer() {
        return buttonsPlayer;
    }
}
