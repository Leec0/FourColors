package be.fourcolors.mvp.view.game;

import be.fourcolors.console.game.cards.Card;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GameView extends BorderPane {

    private HBox cardsPlayer1;
    private HBox cardsPlayer2;
    private HashMap<Card,Button> buttonsPlayer1;
    private HashMap<Card,Button> buttonsPlayer2;
    private HBox centerBox;
    private Button cardPile;
    private Button oneCardButton;
    private ImageView playedCard;


    public GameView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        cardsPlayer1 = new HBox();
        buttonsPlayer1 = new HashMap<>();

        cardsPlayer2 = new HBox();
        buttonsPlayer2 = new HashMap<>();

        centerBox = new HBox();
        cardPile = new Button("Pile");
        oneCardButton = new Button("UNO");
        playedCard = new ImageView();
    }

    private void layoutNodes() {
        centerBox.getChildren().add(cardPile);
        centerBox.getChildren().add(playedCard);
        centerBox.getChildren().add(oneCardButton);

        setTop(cardsPlayer2);
        setBottom(cardsPlayer1);
        setCenter(centerBox);
    }

    public HashMap<Card, Button> getButtonsPlayer1() {
        return buttonsPlayer1;
    }

    public HashMap<Card, Button> getButtonsPlayer2() {
        return buttonsPlayer2;
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
}
