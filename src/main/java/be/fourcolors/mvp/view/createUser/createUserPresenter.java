package be.fourcolors.mvp.view.createUser;

import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginView;

public class createUserPresenter {
    private final LoginView view;
    private final Users model;

    public createUserPresenter(LoginView view, Users model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }
}
