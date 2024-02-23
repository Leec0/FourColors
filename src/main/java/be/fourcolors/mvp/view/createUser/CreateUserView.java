package be.fourcolors.mvp.view.createUser;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.Objects;

public class CreateUserView extends BorderPane {

    private TextField tfUsername;
    private ComboBox<BackgroundColor> cbColor;
    private Button btnCreate;

    private VBox vBox;


    public CreateUserView() {
        initialiseNodes();
        layoutNodes();
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
        this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
    }

    private void initialiseNodes() {
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

        btnCreate = new Button("Confirm");
        btnCreate.setFont(new Font(15));

        vBox = new VBox();
    }

    private void layoutNodes() {
        VBox.setMargin(tfUsername, new Insets(5));
        VBox.setMargin(cbColor, new Insets(5));
        VBox.setMargin(btnCreate, new Insets(40));
        vBox.getChildren().addAll(tfUsername, cbColor, btnCreate);
        vBox.setAlignment(Pos.CENTER);

        tfUsername.setPrefSize(250, 50);
        tfUsername.setMaxSize(250, 50);

        cbColor.setPrefSize(200, 40);
        cbColor.setMaxSize(200, 40);

        this.setCenter(vBox);
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public ComboBox<BackgroundColor> getCbColor() {
        return cbColor;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }
}
