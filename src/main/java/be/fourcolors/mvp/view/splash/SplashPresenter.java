package be.fourcolors.mvp.view.splash;

import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.util.Duration;

public class SplashPresenter {
    private final SplashView view;

    public SplashPresenter(SplashView view) {
        this.view = view;
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(Event::consume);
        PauseTransition transition = new PauseTransition(Duration.seconds(3.5));
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), view.getvBox());
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(actionEvent -> {
            Users model = new Users();
            LoginView loginView = new LoginView();
            LoginPresenter loginPresenter = new LoginPresenter(loginView, model);
            view.getScene().setRoot(loginView);
            loginPresenter.addWindowEventHandlers();
        });
        transition.setOnFinished(actionEvent -> fadeTransition.play());
        transition.play();
    }
}
