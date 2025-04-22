package vue;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class startScreenView extends VBox {

    public startScreenView(Runnable onStart) {
        setAlignment(Pos.CENTER);
        setSpacing(20);

        Button startBtn = new Button("Commencer");
        startBtn.setStyle("-fx-font-size: 18px; -fx-background-color: black;" +
                "-fx-padding: 10px 20px; /*-fx-background-image: url('/gle/start_btn2.jpg');*/" +
                "-fx-background-size: cover; -fx-background-repeat: no-repeat; " +
                "-fx-background-position: center; -fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-border-radius: 50px; -fx-background-radius: 50px;" +
                "-fx-border-color: white;\n" +
                "-fx-border-width: 2px; -fx-background-clip: padding-box;");


        startBtn.setOnAction(e -> onStart.run());

        getChildren().add(startBtn);
    }
}
