package be.fourcolors.mvp.view.settings;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.StringConverter;


import java.util.Objects;

public class SettingsView extends BorderPane {

    private Label laSettings;
    private Button btnChangeName;
    private ComboBox<BackgroundColor> cbChangeColor;
    private Button btnRules;
    private Button btnBack;
    private VBox buttonBox;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
        this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void initialiseNodes() {
        laSettings = new Label("Instellingen");
        laSettings.setFont(new Font(30));

        btnChangeName = new Button("Verander naam");
        btnChangeName.setFont(new Font(20));

        cbChangeColor = new ComboBox<>();
        cbChangeColor.getItems().addAll(BackgroundColor.values());
        cbChangeColor.setConverter(new StringConverter<>() {
            @Override
            public String toString(BackgroundColor backgroundColor) {
                return backgroundColor.getReadableName();
            }

            @Override
            public BackgroundColor fromString(String s) {
                return null;
            }
        });
        cbChangeColor.setPromptText("Kies een kleur");
        cbChangeColor.setStyle("-fx-font-size: 20");
        cbChangeColor.setVisibleRowCount(5);

        btnRules = new Button("Regels");
        btnRules.setFont(new Font(20));

        btnBack = new Button("Terug");
        btnBack.setFont(new Font(20));

        buttonBox = new VBox();
    }

    private void layoutNodes() {
        BorderPane.setAlignment(laSettings, Pos.CENTER);
        BorderPane.setMargin(laSettings, new Insets(50, 0, 0, 0));
        this.setTop(laSettings);

        btnRules.setPrefWidth(200);
        btnChangeName.setPrefWidth(200);
        cbChangeColor.setPrefWidth(200);
        btnBack.setPrefWidth(200);
        buttonBox.getChildren().addAll(btnRules, btnChangeName, cbChangeColor, btnBack);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(btnRules, new Insets(0, 0, 20, 0));
        VBox.setMargin(btnChangeName, new Insets(0, 0, 20, 0));
        VBox.setMargin(cbChangeColor, new Insets(0, 0, 20, 0));
        VBox.setMargin(btnBack, new Insets(40, 0, 0, 0));
        this.setCenter(buttonBox);
    }

    public Button getBtnChangeName() {
        return btnChangeName;
    }

    public ComboBox<BackgroundColor> getCbChangeColor() {
        return cbChangeColor;
    }

    public Button getBtnRules() {
        return btnRules;
    }

    public Button getBtnBack() {
        return btnBack;
    }
}
