package be.fourcolors.mvp.view.settings;

import be.fourcolors.mvp.model.user.Users;

public class SettingsPresenter {

    private final SettingsView view;
    private final Users model;

    public SettingsPresenter(SettingsView view, Users model) {
        this.view = view;
        this.model = model;
    }
}
