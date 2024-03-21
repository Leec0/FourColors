package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.cards.Card;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.*;

public class GameView extends BorderPane {

    private HBox cardsPlayer1;
    private Map<Card, Button> buttonsPlayer;
    private HBox cardsPlayer2;
    private List<ImageView> ivCardsPlayer2;
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
        buttonsPlayer = new HashMap<>();

        cardsPlayer2 = new HBox();
        ivCardsPlayer2 = new ArrayList<>();

        centerBox = new HBox();
        cardPile = new Button();
        oneCardButton = new Button();
        playedCard = new ImageView();
    }

    private void layoutNodes() {
        centerBox.setAlignment(Pos.CENTER);
        HBox.setMargin(playedCard, new Insets(0, 50, 0, 50));
        centerBox.getChildren().add(cardPile);
        centerBox.getChildren().add(playedCard);
        centerBox.getChildren().add(oneCardButton);

        playedCard.setPreserveRatio(true);
        playedCard.setFitWidth(150);
        Image deckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cards/normal/deck.png")));
        ImageView deckView = new ImageView(deckImage);
        deckView.setPreserveRatio(true);
        deckView.setFitWidth(150);
        cardPile.setGraphic(deckView);
        cardPile.setStyle("-fx-background-color: none");
        Image oneCardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
        ImageView oneCardView = new ImageView(oneCardImage);
        oneCardView.setPreserveRatio(true);
        oneCardView.setFitWidth(80);
        oneCardButton.setGraphic(oneCardView);
        oneCardButton.setStyle("-fx-background-color: none");

        cardsPlayer1.setAlignment(Pos.CENTER);
        setMargin(cardsPlayer1, new Insets(20));

        cardsPlayer2.setAlignment(Pos.CENTER);
        setMargin(cardsPlayer2, new Insets(20));

        setBottom(cardsPlayer1);
        setTop(cardsPlayer2);
        setCenter(centerBox);
    }

    public void addButtons(List<Card> cards) {
        cardsPlayer1.getChildren().clear();
        buttonsPlayer.clear();
        for (Card card : cards) {
            Button button = new Button();
            String cardFile = "/images/cards/normal/" + card.getFileName() + ".png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(cardFile)));
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(75);
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: none");
            buttonsPlayer.put(card, button);
            cardsPlayer1.getChildren().add(buttonsPlayer.get(card));
        }
    }

    public void addPlayer2Cards(int amount) {
        cardsPlayer2.getChildren().clear();
        ivCardsPlayer2.clear();
        for (int i = 0; i < amount; i++) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cards/normal/deck.png")));
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(75);
            ivCardsPlayer2.add(imageView);
        }
        cardsPlayer2.getChildren().addAll(ivCardsPlayer2);
    }

    public List<ImageView> getIvCardsPlayer2() {
        return ivCardsPlayer2;
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
