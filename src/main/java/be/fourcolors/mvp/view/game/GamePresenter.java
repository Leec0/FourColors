package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.players.interfaces.Player;
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
import javafx.scene.image.Image;

import java.util.Objects;

public class GamePresenter {
    private final GameView view;
    private final PlayField model;
    private final User user;
    private final HumanPlayer player;

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
            if (player.isPlayerTurn()) {
                model.playerDraw(player);
                updateView();
            }
        });
        view.getOneCardButton().setOnAction(actionEvent -> {
            if (player.canCall()) {
                player.oneCard();
            }
            if (model.playerWithOneCard()) {
                model.callOutPlayers();
            }
            updateView();
        });
    }

    private void updateView() {
        checkForWin();
        setPlayedCard();
        setPlayerCards();
        cardsPlayer2();
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
        String playedCardFile;
        if (playedCard.getColor() == CardColor.WILD) {

        } else {
            playedCardFile = "/images/cards/normal/" + playedCard.getFileName() + ".png";
        }
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
                if (player.isPlayerTurn()) {
                    if (card.getColor() == CardColor.WILD) {
                        setWildColor();
                    }
                    model.playerPlay(player, card);
                    updateView();
                }
            });
        }
    }

    private HumanPlayer setPlayer() {
        for (Player player : model.getPlayers()) {
            if (player.getClass().equals(HumanPlayer.class)) {
                return (HumanPlayer) player;
            }
        }
        return null;
    }

    private void setWildColor() {
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

    private void checkForWin() {
        if (model.gameEnd()) {
            Users users = new Users();
            users.addWin(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Four Colors");
            alert.setHeaderText("Het spel is gedaan.");
            alert.setContentText("De winnaar is player: " + model.getWinningPlayer());
            alert.showAndWait();
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, user);
            view.getScene().setRoot(mainMenuView);
            mainMenuPresenter.addWindowEventHandlers();
        }
    }

    private void cardsPlayer2() {
        view.addPlayer2Cards(model.getPlayers().get(1).getCards().size());
    }
}
