package be.fourcolors.mvp.view.createUser;

import be.fourcolors.mvp.model.exceptions.IllegalUsernameException;
import be.fourcolors.mvp.model.user.BackgroundColor;
import be.fourcolors.mvp.model.user.User;
import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
        view.getCbColor().setOnAction(actionEvent -> {
            Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + view.getCbColor().getValue().getBackgroundFile())));
            view.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
        });
    }

    private void updateView() {

    }
}
