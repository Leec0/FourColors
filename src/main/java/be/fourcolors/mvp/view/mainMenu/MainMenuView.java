package be.fourcolors.mvp.view.mainMenu;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class MainMenuView extends GridPane {

    private Button btnPlay;
    private Button btnSettings;
    private Button btnLogout;
    private ImageView ivLogo;
    private VBox vBox;

    public MainMenuView() {
        initializeNodes();
        layoutNodes();
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
        this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void initializeNodes() {
        btnPlay = new Button("Speel");
        btnPlay.setFont(new Font(20));

        btnSettings = new Button("Instellingen");
        btnSettings.setFont(new Font(20));

        btnLogout = new Button("Uitloggen");
        btnLogout.setFont(new Font(20));

        try {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icon.png")));
            ivLogo = new ImageView(logo);
            ivLogo.setPreserveRatio(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        vBox = new VBox();
        vBox.getChildren().addAll(btnPlay, btnSettings, btnLogout);
    }

    private void layoutNodes() {
        btnPlay.setPrefSize(200, 75);
        btnSettings.setPrefSize(200, 50);
        btnLogout.setPrefSize(200, 50);

        ivLogo.fitWidthProperty().bind(this.widthProperty().divide(1.5));
        ivLogo.fitHeightProperty().bind(this.heightProperty().divide(1.5));

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(30));
        this.setHgap(70);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(btnPlay, new Insets(0, 0, 60, 0));
        VBox.setMargin(btnSettings, new Insets(60, 0, 30, 0));
        VBox.setMargin(btnLogout, new Insets(30, 0, 0, 0));

        this.add(vBox, 0, 0);
        this.add(ivLogo, 1, 0);
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public Button getBtnSettings() {
        return btnSettings;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }
}
