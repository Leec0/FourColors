package be.fourcolors.mvp;

import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import be.fourcolors.mvp.view.splash.SplashPresenter;
import be.fourcolors.mvp.view.splash.SplashView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private final static String ICON_PATH = "/images/icon.png";
    public void start(Stage primaryStage){
        SplashView view = new SplashView();
        SplashPresenter presenter = new SplashPresenter(view);
        primaryStage.setScene(new Scene(view));

        primaryStage.setTitle("Four Colors");
        Image sceneIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_PATH)));
        primaryStage.getIcons().add(sceneIcon);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
        presenter.addWindowEventHandlers();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
