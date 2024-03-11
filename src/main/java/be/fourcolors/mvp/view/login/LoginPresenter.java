package be.fourcolors.mvp.view.login;

import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.createUser.CreateUserPresenter;
import be.fourcolors.mvp.view.createUser.CreateUserView;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;

public class LoginPresenter {
    private final LoginView view;
    private final Users model;

    public LoginPresenter(LoginView view, Users model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnConfirm().setOnAction(actionEvent -> loginOrCreate());
        view.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginOrCreate();
            }
        });
    }

    private void updateView() {

    }

    private void loginOrCreate() {
        try {
            String name = view.getTfUserName().getText();
            User user = new User(name);
            if (model.hasUser(user)) {
                User userModel = model.getUsers().get(name.toLowerCase());
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, userModel);
                view.getScene().setRoot(mainMenuView);
                mainMenuPresenter.addWindowEventHandlers();
            } else {
                CreateUserView createUserView = new CreateUserView();
                createUserView.getTfUsername().setText(name);
                CreateUserPresenter createUserPresenter = new CreateUserPresenter(createUserView, model);
                view.getScene().setRoot(createUserView);
                createUserPresenter.addWindowEventHandlers();
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
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
