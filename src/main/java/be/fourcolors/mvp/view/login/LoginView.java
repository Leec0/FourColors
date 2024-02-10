package be.fourcolors.mvp.view.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
        this.setMinSize(800, 600);
        vBox = new VBox();

        tfUserName = new TextField();
        tfUserName.setPromptText("Username");
        tfUserName.setFont(new Font(20));

        btnConfirm = new Button("Confirm");
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
    }

    public Button getBtnConfirm() {
        return btnConfirm;
    }
}
