package be.fourcolors.mvp.view.mainMenu;


import be.fourcolors.mvp.model.game.PlayField;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.game.GamePresenter;
import be.fourcolors.mvp.view.game.GameView;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import be.fourcolors.mvp.view.settings.SettingsPresenter;
import be.fourcolors.mvp.view.settings.SettingsView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MainMenuPresenter {
    private final MainMenuView view;
    private final User model;

    public MainMenuPresenter(MainMenuView view, User model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnPlay().setOnAction(actionEvent -> {
            AtomicInteger amountOfPlayers = new AtomicInteger();
            Alert alertAmount = new Alert(Alert.AlertType.CONFIRMATION);
            alertAmount.setTitle("Four Colors");
            alertAmount.setHeaderText("Kies het aantal spelers");
            ButtonType players2 = new ButtonType("2");
            ButtonType players3 = new ButtonType("3");
            ButtonType players4 = new ButtonType("4");
            alertAmount.getButtonTypes().setAll(players2, players3, players4);
            alertAmount.showAndWait().ifPresent(buttonEvent1 -> {
                if (buttonEvent1.equals(players2)) {
                    amountOfPlayers.set(2);
                } else if (buttonEvent1.equals(players3)) {
                    amountOfPlayers.set(3);
                } else if (buttonEvent1.equals(players4)) {
                    amountOfPlayers.set(4);
                }
            });
            AtomicReference<String> botType = new AtomicReference<>();
            Alert alertType = new Alert(Alert.AlertType.CONFIRMATION);
            alertType.setTitle("Four Colors");
            alertType.setHeaderText("Kies de moeilijkheidsgraad");
            ButtonType easy = new ButtonType("Makkelijk");
            ButtonType medium = new ButtonType("Gemiddeld");
            ButtonType hard = new ButtonType("Moeilijk");
            alertType.getButtonTypes().setAll(easy, medium, hard);
            alertType.showAndWait().ifPresent(buttonEvent2 -> {
                if (buttonEvent2.equals(easy)) {
                    botType.set("easy");
                } else if (buttonEvent2.equals(medium)) {
                    botType.set("medium");
                } else if (buttonEvent2.equals(hard)) {
                    botType.set("hard");
                }
            });
            PlayField playFieldModel = new PlayField(amountOfPlayers.get(), botType.get());
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameView, playFieldModel, model);
            view.getScene().setRoot(gameView);
            gamePresenter.addWindowEventHandlers();
        });
        view.getBtnSettings().setOnAction(actionEvent -> {
            SettingsView settingsView = new SettingsView();
            SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView, model);
            settingsView.getCbChangeColor().setValue(model.getFavoriteColor());
            settingsView.setBackground(view.getBackground());
            view.getScene().setRoot(settingsView);
            settingsPresenter.addWindowEventHandlers();
        });
        view.getBtnLogout().setOnAction(actionEvent -> {
            Users usersModel = new Users();
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, usersModel);
            view.getScene().setRoot(loginView);
            loginPresenter.addWindowEventHandlers();
        });
    }

    private void updateView() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + model.getFavoriteColor().getBackgroundFile())));
        view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
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
}
