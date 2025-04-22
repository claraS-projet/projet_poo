package vue;

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import modele.map.Location;
import modele.Character.Hero;
import controlleur.gameController;
import modele.AddedForGUI.gameState;

import java.awt.*;

public class locationView extends Pane {
    private gameState gameState;
    private gameController controller;
    private ImageView heroSprite;
    private Location location;

    public locationView(Location loc, gameController controller) {
        this.location = loc;
        this.controller = controller;
        this.prefWidthProperty().bind(controller.getRoot().widthProperty());
        this.prefHeightProperty().bind(controller.getRoot().heightProperty());
        drawLocation(controller);
        drawHero(controller);

    }

    public void drawLocation(gameController controller) {

        Image locBg = new Image("/locations/planet03.png");
        if (locBg.isError()) {
            System.out.println("⚠️ Erreur de chargement de la PLANET !");
        }

        ImageView locView = new ImageView(locBg);
        locView.setFitWidth(700);
        locView.setFitHeight(500);

        locView.setPreserveRatio(false); // ou true si tu veux garder les proportions
        locView.setSmooth(true);

        locView.fitWidthProperty().bind(this.widthProperty());
        locView.fitHeightProperty().bind(this.heightProperty());

        this.getChildren().add(locView);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(60); // arrondi horizontal
        clip.setArcHeight(60); // arrondi vertical

        // Lier le clip à la taille du locationView
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());

        this.setClip(clip);
        //drawBackground();
        //drawHero();
        // drawItems(), drawCharacters(), drawExits() à ajouter ensuite
    }

    private void drawBackground() {
        Location loc = gameState.getHero().getPosition();
        Image bg = new Image("file:resources/backgrounds/" + loc.getName() + ".png");
        ImageView bgView = new ImageView(bg);
        this.getChildren().add(bgView);
    }

    private void drawHero() {
        Image heroImg = new Image("file:resources/sprites/hero.png");
        heroSprite = new ImageView(heroImg);
        heroSprite.setX(400); // à remplacer plus tard par une coordonnée logique
        heroSprite.setY(300);
        this.getChildren().add(heroSprite);
    }

    public void moveHero(double dx, double dy) {
        heroSprite.setX(heroSprite.getX() + dx);
        heroSprite.setY(heroSprite.getY() + dy);
    }

    public boolean tryGo() {
        // Tu pourrais par exemple ici :
        // - vérifier si le héros est proche d’un “portail”
        // - appeler gameState.goTo(....) et retourner true si réussi
        return false;
    }

    public void drawHero(gameController controller) {
        characterView hero = new characterView(
                this, 0, 0, 60, 60,
                "/characters/hero/astronautB_N.png",
                "/characters/hero/astronautB_NE.png",
                "/characters/hero/astronautB_E.png",
                "/characters/hero/astronautB_SE.png",
                "/characters/hero/astronautB_S.png",
                "/characters/hero/astronautB_SW.png",
                "/characters/hero/astronautB_W.png",
                "/characters/hero/astronautB_NW.png"
        );
        this.getChildren().add(hero);
        hero.bindToBottomCenter(this);
        hero.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }




}
