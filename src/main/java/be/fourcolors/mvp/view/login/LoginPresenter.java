package be.fourcolors.mvp.view.login;

import be.fourcolors.mvp.Main;
import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.createUser.CreateUserView;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import javafx.scene.Scene;
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
                    MainMenuView view = new MainMenuView();
                    Scene scene = new Scene(view);
                    Main.window.setScene(scene);
                    Main.window.show();
                } else {
                    CreateUserView view = new CreateUserView();
                    Scene scene = new Scene(view);
                    Main.window.setScene(scene);
                    Main.window.show();
                }
            } catch (IllegalUsernameException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        });
    }

    private void updateView() {

    }
}
