package be.fourcolors.mvp.view.splash;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SplashView extends BorderPane {
    private Label laText;
    private ImageView ivLogo;

    private VBox vBox;

    public SplashView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
        ivLogo = new ImageView(logo);

        laText = new Label("Loading...");

        vBox = new VBox();
    }

    private void layoutNodes() {
        VBox.setMargin(ivLogo, new Insets(50));
        VBox.setMargin(laText, new Insets(50));
        vBox.getChildren().addAll(ivLogo, laText);

        this.setCenter(vBox);
    }
}
