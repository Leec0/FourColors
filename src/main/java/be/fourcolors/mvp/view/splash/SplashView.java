package be.fourcolors.mvp.view.splash;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SplashView extends BorderPane {
    private Label laText;
    private ImageView ivLogo;
    private ImageView ivLoading;
    private VBox vBox;

    public SplashView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
        ivLogo = new ImageView(logo);

        Image loading = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/loading.gif")));
        ivLoading = new ImageView(loading);

        laText = new Label("Loading...");

        vBox = new VBox();
    }

    private void layoutNodes() {
        ivLoading.setFitWidth(600);
        ivLoading.setFitHeight(150);
        ivLoading.setSmooth(true);

        ivLogo.setPreserveRatio(true);
        ivLogo.setFitWidth(600);
        ivLogo.setFitHeight(450);
        ivLogo.setSmooth(true);

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(vBox, new Insets(50));

        vBox.getChildren().addAll(ivLogo, ivLoading);

        this.setCenter(vBox);
    }

}
