package vue;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import modele.Character.Doctor;
import modele.Character.MyCharacter;

import java.util.ArrayList;

public class doctorView extends Pane {

    private Doctor doctor;
    private final ImageView doctorImage;
    private final double width;
    private final double height;

    private final Image imageD;

    private locationView locationView;

    public doctorView(locationView locationView, double panesize, double herosize, String sePath) {
        this.locationView = locationView;
        ArrayList<MyCharacter> characters = this.locationView.getLocation().getCharacters();
        for (MyCharacter character : characters) {
            if (character instanceof Doctor) {
                this.doctor = (Doctor) character;
                this.locationView.setDoctor(this);
            }
        }
        this.width = panesize;
        this.height = panesize;

        this.imageD = new Image(sePath);
        if (imageD.isError()) {
            System.out.println("⚠️ Erreur de chargement de l'image Doctor !");
        }

        doctorImage = new ImageView(imageD);
        doctorImage.setFitWidth(panesize * 2 - 10);
        doctorImage.setFitHeight(panesize * 2 - 10);

        doctorImage.setLayoutX((this.getLayoutX() - 20));
        doctorImage.setLayoutY(this.getLayoutY() - 25);
        this.setPrefWidth(panesize);
        this.setPrefHeight(panesize);
        this.getChildren().add(doctorImage);

        //this.locationView.setDoctor(this);
    }

    public ImageView getImageView() {
        return doctorImage;
    }

    public void bindToBottomCenter(locationView container) {
        this.layoutXProperty().bind(
                container.widthProperty().subtract(50)
        );
        this.layoutYProperty().bind(
                container.heightProperty().subtract(this.prefHeightProperty()).divide(2)
        );
    }

    public Point2D getCenter() {
        return new Point2D(getLayoutX() + getBoundsInLocal().getWidth() / 2,
                getLayoutY() + getBoundsInLocal().getHeight() / 2);
    }

    public Doctor getDoctor() {
        return doctor;
    }

}
