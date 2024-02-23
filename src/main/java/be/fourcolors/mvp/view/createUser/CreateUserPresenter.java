package be.fourcolors.mvp.view.createUser;

import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;
import be.fourcolors.mvp.model.user.BackgroundColor;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginView;
import javafx.scene.control.Alert;

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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (model.hasUser(user)) {
                    //Opent main menu
                    throw new IllegalUsernameException("Username bestaat al!");
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
