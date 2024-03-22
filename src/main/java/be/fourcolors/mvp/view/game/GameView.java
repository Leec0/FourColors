package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.cards.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

public class GameView extends BorderPane {

    private HBox cardsPlayer1;
    private ScrollPane scrollPane;
    private Map<Card, Button> buttonsPlayer;
    private HBox cardsOtherPlayers;
    private List<VBox> infoPlayers;
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
        scrollPane = new ScrollPane(cardsPlayer1);

        cardsOtherPlayers = new HBox();
        infoPlayers = new ArrayList<>();

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

        cardsPlayer1.setAlignment(Pos.BOTTOM_CENTER);
        cardsPlayer1.setStyle("-fx-padding: 20 5 5 5");
        scrollPane.setFitToWidth(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        cardsOtherPlayers.setAlignment(Pos.CENTER);
        setMargin(cardsOtherPlayers, new Insets(10, 20, 10, 0));

        setBottom(scrollPane);
        setTop(cardsOtherPlayers);
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

    public void addPlayer2Cards(int numberOfPlayers, ArrayList<Integer> amount) {
        if (numberOfPlayers == amount.size()) {
            cardsOtherPlayers.getChildren().clear();
            infoPlayers.clear();
            for (int i = 0; i < numberOfPlayers; i++) {
                HBox hBox = new HBox();
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cards/normal/deck.png")));
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(75);
                Label label = new Label("X " + amount.get(i));
                label.setAlignment(Pos.CENTER);
                hBox.getChildren().addAll(imageView, label);
                hBox.setAlignment(Pos.CENTER);
                HBox.setMargin(imageView, new Insets(10));
                VBox vBox = new VBox();
                Label infoPlayer = new Label("Player: " + (i + 2));
                infoPlayer.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(infoPlayer, hBox);
                vBox.setAlignment(Pos.CENTER);
                infoPlayers.add(vBox);
            }
        }
        cardsOtherPlayers.getChildren().addAll(infoPlayers);
        cardsOtherPlayers.setSpacing(50);
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
