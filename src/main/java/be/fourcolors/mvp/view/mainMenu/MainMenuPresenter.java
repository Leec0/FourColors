package be.fourcolors.mvp.view.mainMenu;


import be.fourcolors.mvp.model.user.User;

public class MainMenuPresenter {
    private final MainMenuView view;
    private final User model;

    public MainMenuPresenter(MainMenuView view, User model) {
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
