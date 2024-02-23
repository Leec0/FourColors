package be.fourcolors.mvp.view.settings;

import be.fourcolors.mvp.model.user.User;

public class SettingsPresenter {
    private final SettingsView view;
    private final User model;

    public SettingsPresenter(SettingsView view, User model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getBtnRules().setOnAction(actionEvent -> {

        });
    }

    private void updateView() {

    }
}
