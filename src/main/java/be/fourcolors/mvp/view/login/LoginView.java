package be.fourcolors.mvp.view.login;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;

public class LoginView extends BorderPane {
    private Label laTitle;
    private TextField tfUserName;
    private Button btnConfirm;
    private VBox vBox;

    public LoginView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
        this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));

        this.setMinSize(800, 600);
        vBox = new VBox();

        tfUserName = new TextField();
        tfUserName.setPromptText("Username");
        tfUserName.setFont(new Font(20));

        btnConfirm = new Button("Login");
        btnConfirm.setFont(new Font(15));

        laTitle = new Label("Four Colors");
        laTitle.setFont(new Font(70));
    }

    private void layoutNodes() {
        vBox.setPrefSize(100, 200);
        vBox.setAlignment(Pos.CENTER);

        tfUserName.setPrefWidth(250);
        tfUserName.setMaxWidth(250);
        tfUserName.setAlignment(Pos.CENTER);

        btnConfirm.setAlignment(Pos.CENTER);

        VBox.setMargin(tfUserName, new Insets(0, 0, 20, 0));
        vBox.getChildren().addAll(tfUserName, btnConfirm);

        laTitle.setAlignment(Pos.CENTER);

        this.setCenter(vBox);
        this.setTop(laTitle);
        BorderPane.setAlignment(laTitle, Pos.CENTER);
        BorderPane.setMargin(laTitle, new Insets(75));
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }

    public TextField getTfUserName() {
        return tfUserName;
    }
}
