package be.fourcolors.mvp;

import be.fourcolors.mvp.view.login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private final static String ICON_PATH = "/images/icon.png";
    public void start(Stage primaryStage){
        LoginView loginView = new LoginView();
        Image sceneIcon = new Image(ICON_PATH);
        primaryStage.getIcons().add(sceneIcon);
        primaryStage.setScene(new Scene(loginView));
        primaryStage.setTitle("Four Colors");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
