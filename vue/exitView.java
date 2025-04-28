package vue;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import modele.map.Exit;

public class exitView extends Pane {

    private final Exit exit;
    private final ImageView imageView;
    public String path;

    public exitView(Exit exit, String imagePath) {
        path = imagePath;
        double panetaille = 40;
        double taille = 200;
        this.exit = exit;
        this.setPrefWidth(panetaille);  // taille logique (plus petite que le sprite)
        this.setPrefHeight(panetaille);
        //"/items/exits/craft_racer_NW.png"
        Image image = new Image(imagePath);
        if (image.isError()) {
            System.out.println("⚠️ Erreur de chargement de l'image de l'exit!");
        }
        imageView = new ImageView(image);
        imageView.setFitWidth(taille);
        imageView.setFitHeight(taille);

        imageView.setLayoutX((panetaille - taille) / 2);
        imageView.setLayoutY(((panetaille - taille) / 2) - panetaille / 2);
        this.setPrefWidth(panetaille);
        this.setPrefHeight(panetaille);
        this.getChildren().add(imageView);

        // Tooltip / message survol facultatif
        //Label label = new Label("Vers : " + exit.getWayOut().getName());
        //label.setStyle("-fx-text-fill: white; -fx-font-size: 10px;");
        //label.setLayoutY(70);
        //this.getChildren().add(label);
        //this.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }

    public Exit getExit() {
        return exit;
    }

    public Point2D getCenter() {
        return new Point2D(getLayoutX() + getBoundsInLocal().getWidth() / 2,
                getLayoutY() + getBoundsInLocal().getHeight() / 2);
    }

    public Bounds getVisualBounds() {
        return this.localToParent(imageView.getBoundsInParent());
    }

}
