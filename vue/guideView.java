package vue;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import modele.Character.Guide;
import modele.map.CodeDoor;
import modele.map.KeyDoor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class guideView extends Pane {

    private Guide guide;
    private final ImageView guideImage;
    private final double width;
    private final double height;

    private final Image imageG;


    private locationView locationView;

    public guideView(locationView locationView, double panesize, double herosize,
                         String sePath) {

        this.locationView = locationView;
        this.guide = this.locationView.getLocation().getGuide();
        this.width = panesize;
        this.height = panesize;

        //this.setPrefWidth(width);
        //this.setPrefHeight(height);

        this.imageG = new Image(sePath);
        if (imageG.isError()) {
            System.out.println("⚠️ Erreur de chargement de l'image NORTH !");
        }


        guideImage = new ImageView(imageG); // face vers le bas par défaut
        guideImage.setFitWidth(herosize);
        guideImage.setFitHeight(herosize);

        guideImage.setLayoutX((panesize - herosize) / 2);
        guideImage.setLayoutY(((panesize - herosize) / 2) - panesize / 2);
        this.setPrefWidth(panesize);
        this.setPrefHeight(panesize);
        this.getChildren().add(guideImage);
        //Random random = new Random();
        /*
        locationView.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                double maxX = locationView.getWidth() - this.getPrefWidth() - 20;
                double maxY = locationView.getHeight() - this.getPrefHeight() - 20;

                if (maxX > 20 && maxY > 20) {
                    double x = ThreadLocalRandom.current().nextDouble(20, maxX);
                    double y = ThreadLocalRandom.current().nextDouble(20, maxY);
                    this.setLayoutX(x);
                    this.setLayoutY(y);
                }
            }
        });
        */
        this.locationView.setGuide(this);
    }



    public ImageView getImageView() {
        return guideImage;
    }


    public void bindToBottomCenter(locationView container) {
        this.layoutXProperty().bind(
                container.widthProperty().add(20).subtract(container.widthProperty())
        );
        this.layoutYProperty().bind(
                container.heightProperty().subtract(this.prefHeightProperty()).divide(2)
        );

    }

    public Point2D getCenter() {
        return new Point2D(getLayoutX() + getBoundsInLocal().getWidth() / 2,
                getLayoutY() + getBoundsInLocal().getHeight() / 2);
    }

    public Guide getGuide() {
        return guide;
    }

    public void guideInteraction(){
        String message = this.guide.getmsg();

        // --- Création de la bulle (Label simple) ---
        Label label = new Label(message);
        label.setStyle(
                "-fx-background-color: rgba(0,0,0,0.8);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-radius: 10px;"
        );
        label.setMaxWidth(160);
        label.setWrapText(true);

        // --- Création du Popup ---
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoFix(true);
        popup.setAutoHide(false);
        popup.setHideOnEscape(false);

        // --- Calcul de la position exacte par rapport à l'exitView ---
        Bounds exitBounds = this.localToScene(this.getBoundsInLocal());
        Window window = this.getScene().getWindow();

        double exitCenterX = exitBounds.getMinX() + (exitBounds.getWidth() / 2);
        double popupWidth = 160; // même que MaxWidth du label
        double popupX = window.getX() + this.getScene().getX() + exitCenterX - popupWidth / 2;

        double popupY = window.getY() + this.getScene().getY() + exitBounds.getMinY() - 50; // au-dessus de l'exit

        // --- Affichage du popup ---
        popup.show(window, popupX, popupY);

        // --- Fade in doux ---
        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // --- Fermeture automatique après 3 secondes ---
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> popup.hide());
        pause.play();
    }

}
