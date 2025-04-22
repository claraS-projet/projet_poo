package vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

    private Pane locationView;

    public characterView(Pane locationView, double x, double y, double width, double height,
                         String northPath, String northEastPath, String eastPath, String southEastPath,
                         String southPath, String southWestPath, String westPath, String northWestPath) {
        this.locationView = locationView;
        this.width = width;
        this.height = height;

        this.setPrefWidth(width);
        this.setPrefHeight(height);

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
        characterImage.setFitWidth(width);
        characterImage.setFitHeight(height);

        this.getChildren().add(characterImage);
        setLayoutX(x);
        setLayoutY(y);
    }

    public void move(double dx, double dy) {
        double newX = getLayoutX() + dx;
        double newY = getLayoutY() + dy;

        double maxX = locationView.getWidth() - width;
        double maxY = locationView.getHeight() - height;

        if (newX < 0) newX = 0;
        if (newY < 0) newY = 0;
        if (newX > maxX) newX = maxX;
        if (newY > maxY) newY = maxY;

        setLayoutX(newX);
        setLayoutY(newY);

        // Direction diagonale ou simple
        if (dx > 0 && dy < 0) {
            characterImage.setImage(imageNE);
        } else if (dx > 0 && dy > 0) {
            characterImage.setImage(imageSE);
        } else if (dx < 0 && dy > 0) {
            characterImage.setImage(imageSW);
        } else if (dx < 0 && dy < 0) {
            characterImage.setImage(imageNW);
        } else if (dx > 0) {
            characterImage.setImage(imageE);
        } else if (dx < 0) {
            characterImage.setImage(imageW);
        } else if (dy > 0) {
            characterImage.setImage(imageS);
        } else if (dy < 0) {
            characterImage.setImage(imageN);
        }
    }

    public ImageView getImageView() {
        return characterImage;
    }


    public void bindToBottomCenter(Pane container) {
        this.layoutXProperty().bind(
                container.widthProperty().subtract(this.prefWidthProperty()).divide(2)
        );
        this.layoutYProperty().bind(
                container.heightProperty().subtract(this.prefHeightProperty()).subtract(20)
        );
    }

}
