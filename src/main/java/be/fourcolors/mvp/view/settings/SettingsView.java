package be.fourcolors.mvp.view.settings;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.Objects;

public class SettingsView extends BorderPane{
    private Label laSettings;
    private TextField tfUsername;
    private ComboBox<BackgroundColor> cbColor;
    private Button btnSave;
    private Button btnRules;

    private VBox vBox;


    public SettingsView() {
        initialiseNodes();
        layoutNodes();
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
        this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void initialiseNodes() {
        laSettings = new Label("Settings");
        laSettings.setFont(new Font(30));

        tfUsername = new TextField();
        tfUsername.setPromptText("Username");
        tfUsername.setFont(new Font(20));

        cbColor = new ComboBox<>();
        cbColor.getItems().addAll(BackgroundColor.values());
        cbColor.setConverter(new StringConverter<>() {
            @Override
            public String toString(BackgroundColor backgroundColor) {
                return backgroundColor.getReadableName();
            }

            @Override
            public BackgroundColor fromString(String s) {
                return null;
            }
        });
        cbColor.setPromptText("Kies een kleur");
        cbColor.setStyle("-fx-font-size: 18");
        cbColor.setVisibleRowCount(5);

        btnSave = new Button("Save");
        btnSave.setFont(new Font(15));

        btnRules = new Button("Rules");
        btnRules.setFont(new Font(20));

        vBox = new VBox();
    }

    private void layoutNodes() {
        VBox.setMargin(laSettings, new Insets(10));
        VBox.setMargin(tfUsername, new Insets(5));
        VBox.setMargin(cbColor, new Insets(5));
        VBox.setMargin(btnSave, new Insets(40));
        vBox.getChildren().addAll(laSettings, tfUsername, cbColor, btnSave);
        vBox.setAlignment(Pos.CENTER);

        tfUsername.setPrefSize(250, 50);
        tfUsername.setMaxSize(250, 50);

        cbColor.setPrefSize(200, 40);
        cbColor.setMaxSize(200, 40);

        setMargin(btnRules, new Insets(50));
        setAlignment(btnRules, Pos.CENTER);

        this.setCenter(vBox);
        this.setBottom(btnRules);
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public ComboBox<BackgroundColor> getCbColor() {
        return cbColor;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnRules() {
        return btnRules;
    }
}
