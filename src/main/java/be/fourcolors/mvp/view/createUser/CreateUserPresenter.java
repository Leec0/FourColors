package be.fourcolors.mvp.view.createUser;

import be.fourcolors.mvp.model.user.BackgroundColor;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Objects;

public class CreateUserPresenter {
    private final CreateUserView view;
    private final Users model;

    public CreateUserPresenter(CreateUserView view, Users model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnCreate().setOnAction(actionEvent -> {
            try {
                String name = view.getTfUsername().getText();
                BackgroundColor backgroundColor = view.getCbColor().getValue();
                User user = new User(name, backgroundColor);
                if (model.hasUser(user)) {
                    throw new IllegalArgumentException("Username bestaat al!");
                } else {
                    model.addUser(user);
                    MainMenuView mainMenuView = new MainMenuView();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, user);
                    view.getScene().setRoot(mainMenuView);
                }
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("FIX DEZE SHIT");
                alert.showAndWait();
            }
        });
        view.getCbColor().setOnAction(actionEvent -> {
            Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + view.getCbColor().getValue().getBackgroundFile())));
            view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        });
        view.getBtnBack().setOnAction(actionEvent -> {
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, model);
            view.getScene().setRoot(loginView);
        });
    }

    private void updateView() {

    }
}
