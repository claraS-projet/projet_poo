package vue;

import controlleur.gameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.AddedForGUI.gameState;
import modele.map.Location;

public class mainContainer extends HBox {

    private VBox locationPane;
    private ui_Vbox uiPane;

    public mainContainer(gameController controller) {
        gameState state = controller.getGameState();

        // Configuration du HBox principal
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        // VBox principale pour la LocationView (vue du lieu actuel)
        locationPane = new VBox();
        locationPane.setAlignment(Pos.CENTER);
        locationPane.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(locationPane, Priority.ALWAYS);
        HBox.setHgrow(locationPane, Priority.ALWAYS);


        uiPane = new ui_Vbox(controller);
        uiPane.setStyle("-fx-background-color: black; -fx-background-radius: 30px;");
        uiPane.setAlignment(Pos.CENTER);
        uiPane.setSpacing(10);
        uiPane.setPadding(new Insets(10));
        VBox.setVgrow(uiPane, Priority.ALWAYS);
        HBox.setHgrow(uiPane, Priority.ALWAYS);


        this.getChildren().addAll(locationPane, uiPane);

        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            double totalWidth = newVal.doubleValue();
            double spacing = this.getSpacing();
            double padding = this.getPadding().getLeft() + this.getPadding().getRight();
            double usableWidth = totalWidth - spacing - padding;

            locationPane.setPrefWidth(usableWidth * 0.75);
            uiPane.setPrefWidth(usableWidth * 0.25);
        });

        // Bind hauteur des VBox à la hauteur du HBox
        this.heightProperty().addListener((obs, oldVal, newVal) -> {
            double h = newVal.doubleValue();
            locationPane.setPrefHeight(h);
            uiPane.setPrefHeight(h);
        });

        // Ajout de la LocationView après le binding de largeur
        locationView location = new locationView(controller.getGameState().getCurrLocation(), controller);
        location.prefWidthProperty().bind(locationPane.widthProperty());
        location.prefHeightProperty().bind(locationPane.heightProperty());
        locationPane.getChildren().add(location);
        //locationPane.setStyle("-fx-background-color: transparent; -fx-border-radius: 30px;-fx-background-repeat: no-repeat;" +
        //        "-fx-background-radius: 50px;-fx-background-clip: padding-box;-fx-background-size: cover;");



    }

    public void updateUI(gameController controller){
        uiPane = new ui_Vbox(controller);
    }
    public void updateLocView(Location loc, gameController controller){
        locationPane.getChildren().clear();
        locationView newLocView = new locationView(loc, controller);
        locationPane.getChildren().add(newLocView);
    }

    public ui_Vbox getUiPane() {
        return uiPane;
    }
}
