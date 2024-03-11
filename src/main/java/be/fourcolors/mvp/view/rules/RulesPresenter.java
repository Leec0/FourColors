package be.fourcolors.mvp.view.rules;

import be.fourcolors.mvp.model.user.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class RulesPresenter {
    private final RulesView view;
    private User model;

    public RulesPresenter(RulesView view, User model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

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
