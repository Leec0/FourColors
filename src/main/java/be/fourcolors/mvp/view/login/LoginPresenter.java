package be.fourcolors.mvp.view.login;

import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (model.hasUser(user)) {
                    //Opent main menu
                    alert.setHeaderText("De user met naam " + name + " bestaat al.");
                } else {
                    //Opent user create view
                    alert.setHeaderText("De user met naam " + name + " is aangemaakt.");
                    model.addUser(user);
                }
                alert.showAndWait();
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
