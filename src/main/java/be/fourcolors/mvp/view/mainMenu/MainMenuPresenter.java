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
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

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
            PlayField playFieldModel = new PlayField();
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

    }
}
