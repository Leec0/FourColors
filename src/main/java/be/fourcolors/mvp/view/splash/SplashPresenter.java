package be.fourcolors.mvp.view.splash;

import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class SplashPresenter {
    private final SplashView view;

    public SplashPresenter(SplashView view) {
        this.view = view;
    }

    public void addWindowEventHandlers() {
        PauseTransition transition = new PauseTransition(Duration.seconds(3));
        transition.setOnFinished(actionEvent -> {
            Users model = new Users();
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, model);
            view.getScene().setRoot(loginView);
            loginPresenter.addWindowEventHandlers();
        });
        transition.play();
    }
}
