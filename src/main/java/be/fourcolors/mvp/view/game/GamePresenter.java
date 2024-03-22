package be.fourcolors.mvp.view.game;

import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.game.cards.CardType;
import be.fourcolors.mvp.model.game.players.interfaces.BotBase;
import be.fourcolors.mvp.model.game.players.interfaces.Player;
import be.fourcolors.mvp.model.game.cards.Card;
import be.fourcolors.mvp.model.game.cards.CardColor;
import be.fourcolors.mvp.model.game.players.HumanPlayer;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.animation.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class GamePresenter {
    private final GameView view;
    private final PlayField model;
    private final User user;
    private final HumanPlayer player;
    private final PauseTransition pauseTransition = new PauseTransition(Duration.millis(250));
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private boolean alterShown;


    public GamePresenter(GameView view, PlayField model, User user) {
        this.view = view;
        this.model = model;
        this.user = user;
        this.player = setPlayer();
        alterShown = false;
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + user.getFavoriteColor().getBackgroundFile())));
        view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        if (!model.gameEnd()) {
            view.getCardPile().setOnAction(actionEvent -> {
                if (player.isPlayerTurn()) {
                    model.playerDraw(player);
                    updateView();
                    playBots();
                    checkForWin();
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
    }

    private void updateView() {
        setPlayedCard();
        setPlayerCards();
        cardsPlayer2();
        checkForWin();
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
                windowEvent.consume();
                if (buttonType == buttonTypeYes) {
                    Users users = new Users();
                    users.addWin(user);
                    MainMenuView mainMenuView = new MainMenuView();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, user);
                    view.getScene().setRoot(mainMenuView);
                    mainMenuPresenter.addWindowEventHandlers();
                }
            });
        });
    }

    private void playBots() {
        pauseTransition.setOnFinished(actionEvent1 -> {
            if (model.getPlayers().get(model.getPlayerTurn()) instanceof BotBase) {
                model.botsPlay();
                updateView();
                if (model.getPlayers().get(model.getPlayerTurn()) instanceof BotBase) {
                    pauseTransition.play();
                }
            }
        });
        pauseTransition.play();
    }

    private void setPlayedCard() {
        Card playedCard = model.getPlayedCard();
        String playedCardFile;
        if (playedCard.getColor() == CardColor.WILD) {
            if (playedCard.getType() == CardType.DRAW) {
                playedCardFile = switch (model.getWildCardColor()) {
                    case RED -> "/images/cards/normal/Wild_Draw_Red.png";
                    case BLUE -> "/images/cards/normal/Wild_Draw_Blue.png";
                    case GREEN -> "/images/cards/normal/Wild_Draw_Green.png";
                    case YELLOW -> "/images/cards/normal/Wild_Draw_Yellow.png";
                    default -> "/images/cards/normal/Wild_Draw.png";
                };
            } else {
                playedCardFile = switch (model.getWildCardColor()) {
                    case RED -> "/images/cards/normal/Wild_Wild_Red.png";
                    case BLUE -> "/images/cards/normal/Wild_Wild_Blue.png";
                    case GREEN -> "/images/cards/normal/Wild_Wild_Green.png";
                    case YELLOW -> "/images/cards/normal/Wild_Wild_Yellow.png";
                    default -> "/images/cards/normal/Wild_Wild.png";
                };
            }
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
            if (player.isPlayerTurn() && !model.gameEnd()) {
                button.setOnAction(actionEvent -> {
                    if (card.getColor() == CardColor.WILD) {
                        setWildColor();
                    }
                    model.playerPlay(player, card);
                    updateView();
                    playBots();
                });
            }
            animatePlayerButton(button);
        }
    }

    private void animatePlayerButton(Button button) {
        button.setOnMouseEntered(mouseEvent -> {
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), button);
            translateTransition.setToY(-20);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), button);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);

            ParallelTransition parallelTransition = new ParallelTransition(translateTransition, scaleTransition);
            parallelTransition.play();
        });
        button.setOnMouseExited(mouseEvent -> {
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), button);
            translateTransition.setToY(0);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), button);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            ParallelTransition parallelTransition = new ParallelTransition(translateTransition, scaleTransition);
            parallelTransition.play();
        });
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
            alert.setTitle("Four Colors");
            alert.setHeaderText("Het spel is gedaan.");
            alert.setContentText("De winnaar is player: " + model.getWinningPlayer());
            alert.setOnHidden(event -> {
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, user);
                view.getScene().setRoot(mainMenuView);
                mainMenuPresenter.addWindowEventHandlers();
            });
            if (!alert.isShowing() && !alterShown) {
                alterShown = true;
                alert.show();
            }
        }
    }

    private void cardsPlayer2() {
        ArrayList<Integer> amount = new ArrayList<>();
        for (Player playerInfo : model.getPlayers()) {
            if (playerInfo != player) {
                amount.add(playerInfo.getCards().size());
            }
        }
        view.addPlayer2Cards(model.getPlayers().size() - 1, amount);
    }
}