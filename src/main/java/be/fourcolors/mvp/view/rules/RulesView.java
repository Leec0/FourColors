package be.fourcolors.mvp.view.rules;

import be.fourcolors.mvp.model.user.BackgroundColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;

public class RulesView extends BorderPane {
    private Button btnReturn;
    private Image iRules;
    private ImageView ivRules;
    private ScrollBar scrollBar;
public RulesView() {
    initializeNodes();
    layoutNodes();
    Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/backgrounds/" + BackgroundColor.WHITE.getBackgroundFile())));
    this.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true))));
}


    private void initializeNodes() {
        btnReturn = new Button("Terug");
        btnReturn.setFont(new Font(20));

        try {
            iRules = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/rules/Regels.PNG")));
            ivRules = new ImageView(iRules);
            ivRules.setPreserveRatio(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scrollBar = new ScrollBar();
    }
    private void layoutNodes() {
        btnReturn.setPrefWidth(200);
        ivRules.fitWidthProperty().bind(this.widthProperty().divide(1.5));
        ivRules.fitHeightProperty().bind(this.heightProperty().divide(1.5));

        this.setCenter(ivRules);
        this.setBottom(btnReturn);
        this.setRight(scrollBar);

        btnReturn.setAlignment(Pos.BOTTOM_RIGHT);
        setMargin(btnReturn, new Insets(40, 0, 0, 0));
    }

    public Button getBtnReturn() {
        return btnReturn;
    }

    public Image getiRules() {
        return iRules;
    }

    public ImageView getIvRules() {
        return ivRules;
    }

    public ScrollBar getScrollBar() {
        return scrollBar;
    }
}

