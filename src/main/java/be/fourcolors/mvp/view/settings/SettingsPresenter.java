package be.fourcolors.mvp.view.settings;

import be.fourcolors.mvp.model.user.BackgroundColor;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import be.fourcolors.mvp.view.rules.RulesPresenter;
import be.fourcolors.mvp.view.rules.RulesView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;


public class SettingsPresenter {

    private final SettingsView view;
    private User model;

    public SettingsPresenter(SettingsView view, User model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnRules().setOnAction(actionEvent -> {
            RulesView rulesView = new RulesView();
            RulesPresenter rulesPresenter = new RulesPresenter(rulesView, model);
            view.getScene().setRoot(rulesView);
            rulesPresenter.addWindowEventHandlers();
        });
        view.getBtnChangeName().setOnAction(actionEvent -> {
            Users users = new Users();
            TextInputDialog td = new TextInputDialog(model.getName());
            td.setTitle("Verander gebruikersnaam");
            td.setHeaderText("Geef hieronder een nieuwe gebruikersnaam in.");
            td.showAndWait();
            String newName = td.getEditor().getText();
            try {
                User userNew = new User(newName, model);
                if (!newName.equalsIgnoreCase(model.getName())) {
                    if (users.hasUser(userNew)) {
                        throw new IllegalArgumentException("Username bestaat al!");
                    }
                }
                users.updateUser(model, userNew);
                model = userNew;
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        });
        view.getCbChangeColor().setOnAction(actionEvent -> {
            Users users = new Users();
            BackgroundColor backgroundColor = view.getCbChangeColor().getValue();
            model.setFavoriteColor(backgroundColor);
            users.updateUser(model);
            Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + view.getCbChangeColor().getValue().getBackgroundFile())));
            view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        });
        view.getBtnBack().setOnAction(actionEvent -> {
            MainMenuView mainMenuView = new MainMenuView();
            MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, model);
            view.getScene().setRoot(mainMenuView);
            mainMenuPresenter.addWindowEventHandlers();
        });
    }

    private void updateView() {
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
