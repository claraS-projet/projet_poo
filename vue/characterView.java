package vue;

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
import javafx.util.Duration;
import modele.map.CodeDoor;
import modele.map.KeyDoor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class characterView extends Pane {

    private final ImageView characterImage;
    private final double width;
    private final double height;

    private final Image imageN;
    private final Image imageNE;
    private final Image imageE;
    private final Image imageSE;
    private final Image imageS;
    private final Image imageSW;
    private final Image imageW;
    private final Image imageNW;

    private locationView locationView;

    public characterView(locationView locationView, double panesize, double herosize,
                         String northPath, String northEastPath, String eastPath, String southEastPath,
                         String southPath, String southWestPath, String westPath, String northWestPath
                        /*, double pos*/) {
        this.locationView = locationView;
        this.width = panesize;
        this.height = panesize;

        //this.setPrefWidth(width);
        //this.setPrefHeight(height);

        this.imageN = new Image(northPath);
        if (imageN.isError()) {
            System.out.println("⚠️ Erreur de chargement de l'image NORTH !");
        }
        this.imageNE = new Image(northEastPath);
        this.imageE = new Image(eastPath);
        this.imageSE = new Image(southEastPath);
        this.imageS = new Image(southPath);
        this.imageSW = new Image(southWestPath);
        this.imageW = new Image(westPath);
        this.imageNW = new Image(northWestPath);

        characterImage = new ImageView(imageS); // face vers le bas par défaut
        characterImage.setFitWidth(herosize);
        characterImage.setFitHeight(herosize);

        characterImage.setLayoutX((panesize - herosize) / 2);
        characterImage.setLayoutY(((panesize - herosize) / 2) - panesize / 2);
        this.setPrefWidth(panesize);
        this.setPrefHeight(panesize);
        this.getChildren().add(characterImage);
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
        this.locationView.setHero(this);
    }

    public void move(double dx, double dy) {

        double odlX = getLayoutX();
        double odlY = getLayoutY();

        double margin = 10;

        double newX = odlX + dx;
        double newY = odlY + dy;

        double hitboxMargin = 8;

        Bounds heroBounds = new BoundingBox(
                newX + hitboxMargin,
                newY + hitboxMargin,
                getPrefWidth() - hitboxMargin,
                getPrefHeight() - hitboxMargin
        );



        for (Node node : locationView.getChildren()) {
            double heroCenterX = newX + getPrefWidth() / 2.0;
            double heroCenterY = newY + getPrefHeight() / 2.0;
            if (node instanceof exitView exit) {
                Bounds exitBounds = new BoundingBox(
                        exit.getLayoutX() + 6,
                        exit.getLayoutY() - 2,
                        exit.getPrefWidth() + 2,
                        exit.getPrefHeight() - 20
                );

                double exitCenterX = exit.getLayoutX() + exit.getPrefWidth() / 2.0;
                double exitCenterY = exit.getLayoutY() + exit.getPrefHeight() / 2.0;
                if (heroBounds.intersects(exitBounds)) {
                    // Le mouvement pousse vers le centre du vaisseau
                    if ((dx > 0 && heroCenterX < exitCenterX) ||
                            (dx < 0 && heroCenterX > exitCenterX) ||
                            (dy > 0 && heroCenterY < exitCenterY) ||
                            (dy < 0 && heroCenterY > exitCenterY)) {

                        locationView.showExitMessage(exit);
                        return;
                    }
                }
            }
            if (node instanceof guideView guide) {
                Bounds exitBounds = new BoundingBox(
                    guide.getLayoutX() + 6,
                        guide.getLayoutY() - 6,
                        guide.getPrefWidth() - 8,
                        guide.getPrefHeight() - 10
                );
                double exitCenterX = guide.getLayoutX() + guide.getPrefWidth() / 2.0;
                double exitCenterY = guide.getLayoutY() + guide.getPrefHeight() / 2.0;
                if (heroBounds.intersects(exitBounds)) {
                    // Le mouvement pousse vers le centre du vaisseau
                    if ((dx > 0 && heroCenterX < exitCenterX) ||
                            (dx < 0 && heroCenterX > exitCenterX) ||
                            (dy > 0 && heroCenterY < exitCenterY) ||
                            (dy < 0 && heroCenterY > exitCenterY)) {

                        locationView.showGuideExitMessage();
                        return;
                    }
                }
            }
            if (node instanceof doctorView guide) {
                Bounds exitBounds = new BoundingBox(
                        guide.getLayoutX() + 6,
                        guide.getLayoutY() - 6,
                        guide.getPrefWidth() - 8,
                        guide.getPrefHeight() - 10
                );
                double exitCenterX = guide.getLayoutX() + guide.getPrefWidth() / 2.0;
                double exitCenterY = guide.getLayoutY() + guide.getPrefHeight() / 2.0;
                if (heroBounds.intersects(exitBounds)) {
                    // Le mouvement pousse vers le centre du vaisseau
                    if ((dx > 0 && heroCenterX < exitCenterX) ||
                            (dx < 0 && heroCenterX > exitCenterX) ||
                            (dy > 0 && heroCenterY < exitCenterY) ||
                            (dy < 0 && heroCenterY > exitCenterY)) {

                        locationView.doctorInteraction();
                        return;
                    }
                }
            }
        }


        double maxX = locationView.getWidth() - getPrefWidth() - margin;
        double maxY = locationView.getHeight() - getPrefHeight() - margin;

        if (newX < margin) newX = margin;
        if (newY < margin) newY = margin;
        if (newX > maxX) newX = maxX;
        if (newY > maxY) newY = maxY;

        if (layoutXProperty().isBound()) layoutXProperty().unbind();
        if (layoutYProperty().isBound()) layoutYProperty().unbind();

        setLayoutX(newX);
        setLayoutY(newY);
        this.toFront();

        // Affichage du sprite selon direction
        if (dx > 0 && dy < 0) characterImage.setImage(imageNE);
        else if (dx > 0 && dy > 0) characterImage.setImage(imageSE);
        else if (dx < 0 && dy > 0) characterImage.setImage(imageSW);
        else if (dx < 0 && dy < 0) characterImage.setImage(imageNW);
        else if (dx > 0) characterImage.setImage(imageE);
        else if (dx < 0) characterImage.setImage(imageW);
        else if (dy > 0) characterImage.setImage(imageS);
        else if (dy < 0) characterImage.setImage(imageN);
    }





    public ImageView getImageView() {
        return characterImage;
    }


    public void bindToBottomCenter(locationView container) {
        this.layoutXProperty().bind(
                container.widthProperty().subtract(this.prefWidthProperty()).divide(2)
        );
        this.layoutYProperty().bind(
                container.heightProperty().subtract(this.prefHeightProperty()).subtract(20)
        );

    }

    public Point2D getCenter() {
        return new Point2D(getLayoutX() + getBoundsInLocal().getWidth() / 2,
                getLayoutY() + getBoundsInLocal().getHeight() / 2);
    }



}
