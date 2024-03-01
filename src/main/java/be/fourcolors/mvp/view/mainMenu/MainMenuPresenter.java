package be.fourcolors.mvp.view.mainMenu;


import be.fourcolors.mvp.model.user.BackgroundColor;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("To be added.");
            alert.showAndWait();
        });
        view.getBtnSettings().setOnAction(actionEvent -> {
            SettingsView settingsView = new SettingsView();
            SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView, model);
            settingsView.getCbChangeColor().setValue(model.getFavoriteColor());
            settingsView.setBackground(view.getBackground());
            view.getScene().setRoot(settingsView);
        });
        view.getBtnLogout().setOnAction(actionEvent -> {
            Users usersModel = new Users();
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, usersModel);
            view.getScene().setRoot(loginView);
        });
    }

    private void updateView() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + model.getFavoriteColor().getBackgroundFile())));
        view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }
}
