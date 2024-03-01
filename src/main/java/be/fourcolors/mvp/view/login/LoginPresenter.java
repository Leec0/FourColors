package be.fourcolors.mvp.view.login;

import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.createUser.CreateUserPresenter;
import be.fourcolors.mvp.view.createUser.CreateUserView;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.scene.control.Alert;

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
        view.getBtnConfirm().setOnAction(actionEvent -> {
            try {
                String name = view.getTfUserName().getText();
                User user = new User(name);
                if (model.hasUser(user)) {
                    User userModel = model.getUsers().get(name);
                    MainMenuView mainMenuView = new MainMenuView();
                    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(mainMenuView, userModel);
                    view.getScene().setRoot(mainMenuView);
                } else {
                    CreateUserView createUserView = new CreateUserView();
                    createUserView.getTfUsername().setText(name);
                    CreateUserPresenter createUserPresenter = new CreateUserPresenter(createUserView, model);
                    view.getScene().setRoot(createUserView);
                }
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        });
    }

    private void updateView() {

    }
}
