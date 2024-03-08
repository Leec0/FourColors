package be.fourcolors.mvp.view.rules;

import be.fourcolors.console.menu.User;

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
}
