package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.Player;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.players.HumanPlayer;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;

import java.util.Objects;

public class GamePresenter {
    private final GameView view;
    private final PlayField model;
    private final User user;
    private final Player player;

    public GamePresenter(GameView view, PlayField model, User user) {
        this.view = view;
        this.model = model;
        this.user = user;
        this.player = setPlayer();
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getCardPile().setOnAction(actionEvent -> {
            model.playerDraw(player);
            updateView();
        });
    }

    private void updateView() {
        setPlayedCard();
        setPlayerCards();
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Four Colors");
            alert.setHeaderText("Ben je zeker dat je het spel wilt afsluiten.");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeYes) {
                    view.getScene().getWindow().hide();
                } else {
                    windowEvent.consume();
                }
            });
        });
    }

    private void setPlayedCard() {
        Card playedCard = model.getPlayedCard();
        String playedCardFile = "/images/cards/normal/" + playedCard.getFileName() + ".png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(playedCardFile)));
        view.getPlayedCard().setImage(image);
    }


    private void setPlayerCards() {
        view.addButtons(player.getCards());
        addPlayerCardsEvent();
    }

    private void addPlayerCardsEvent() {
        for (Card card : view.getButtonsPlayer().keySet()) {
            Button button = view.getButtonsPlayer().get(card);
            button.setOnAction(actionEvent -> {
                if (card.getColor() == CardColor.WILD) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Four Colors");
                    alert.setHeaderText("Kies de kleur van de wild card.");
                    ButtonType buttonTypeRed = new ButtonType("Rood");
                    ButtonType buttonTypeGreen = new ButtonType("Groen");
                    ButtonType buttonTypeBlue = new ButtonType("Blauw");
                    ButtonType buttonTypeYellow = new ButtonType("Geel");
                    alert.getButtonTypes().setAll(buttonTypeRed, buttonTypeGreen, buttonTypeBlue, buttonTypeYellow);
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType.equals(buttonTypeRed)) {
                            model.setWildColor(CardColor.RED);
                        } else if (buttonType.equals(buttonTypeGreen)) {
                            model.setWildColor(CardColor.GREEN);
                        } else if (buttonType.equals(buttonTypeBlue)) {
                            model.setWildColor(CardColor.BLUE);
                        } else if (buttonType.equals(buttonTypeYellow)) {
                            model.setWildColor(CardColor.YELLOW);
                        }
                    });
                }
                model.playerPlay(player, card);
                updateView();
                if (model.gameEnd()) {
                    Users users = new Users();
                    users.addWin(user);
                    MainMenuView mainMenuView = new MainMenuView();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, user);
                    view.getScene().setRoot(mainMenuView);
                    mainMenuPresenter.addWindowEventHandlers();
                }
            });
        }
    }

    private Player setPlayer() {
        for (Player player : model.getPlayers()) {
            if (player.getClass().equals(HumanPlayer.class)) {
                return player;
            }
        }
        return null;
    }
}
