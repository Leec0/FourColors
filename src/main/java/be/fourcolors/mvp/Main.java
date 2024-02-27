package be.fourcolors.mvp;

import be.fourcolors.mvp.model.user.Users;
import be.fourcolors.mvp.view.create.CreateView;
import be.fourcolors.mvp.view.createUser.CreateUserPresenter;
import be.fourcolors.mvp.view.createUser.CreateUserView;
import be.fourcolors.mvp.view.login.LoginPresenter;
import be.fourcolors.mvp.view.login.LoginView;
import be.fourcolors.mvp.view.mainMenu.MainMenuPresenter;
import be.fourcolors.mvp.view.mainMenu.MainMenuView;
import be.fourcolors.mvp.view.settings.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private final static String ICON_PATH = "/images/icon.png";
    public static Stage window;
    public void start(Stage primaryStage){
        Main.window = primaryStage;
        Users model = new Users();
        CreateUserView view = new CreateUserView();
        CreateUserPresenter presenter = new CreateUserPresenter(view, model);

        window.setScene(new Scene(view));
        window.setTitle("Four Colors");
        Image sceneIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_PATH)));
        window.getIcons().add(sceneIcon);
        window.setMinHeight(600);
        window.setMinWidth(800);
        window.setHeight(600);
        window.setWidth(800);
        window.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
