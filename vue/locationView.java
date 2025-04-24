package vue;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import modele.map.Location;
import modele.Character.Hero;
import controlleur.gameController;
import modele.AddedForGUI.gameState;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class locationView extends Pane {
    private gameState gameState;
    private gameController controller;
    private characterView hero;
    private Location location;
    private final Set<KeyCode> keysPressed = new HashSet<>();

    public locationView(Location loc, gameController controller) {
        super();

        this.location = loc;
        this.controller = controller;

        this.prefWidthProperty().bind(controller.getRoot().widthProperty());
        this.prefHeightProperty().bind(controller.getRoot().heightProperty());

        drawLocation(controller);
        drawHero(controller);

        this.setFocusTraversable(true); // Obligatoire pour capter les touches
        this.setOnKeyPressed(e -> {
            keysPressed.add(e.getCode());
            handleMovement();
        });

        this.setOnKeyReleased(e -> {
            keysPressed.remove(e.getCode());
        });


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
        Platform.runLater(() -> this.requestFocus());
        //drawBackground();
        //drawHero();
        // drawItems(), drawCharacters(), drawExits() à ajouter ensuite



        controller.setCurrentlocView(this);
    }

    private void drawBackground() {
        Location loc = gameState.getHero().getPosition();
        Image bg = new Image("file:resources/backgrounds/" + loc.getName() + ".png");
        ImageView bgView = new ImageView(bg);
        this.getChildren().add(bgView);
    }

    private void drawHero() {
        Image heroImg = new Image("file:resources/sprites/hero.png");
        ImageView heroSprite = new ImageView(heroImg);
        heroSprite.setX(400); // à remplacer plus tard par une coordonnée logique
        heroSprite.setY(300);
        this.getChildren().add(heroSprite);
    }



    public boolean tryGo() {
        // Tu pourrais par exemple ici :
        // - vérifier si le héros est proche d’un “portail”
        // - appeler gameState.goTo(....) et retourner true si réussi
        return false;
    }

    public void drawHero(gameController controller) {
        characterView hero = new characterView(
                controller.getCurrlocationView(), 40, 400,
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
        //hero.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

    }

    private void handleMovement() {
        double dx = 0;
        double dy = 0;
        double speed = 10;

        if (keysPressed.contains(KeyCode.UP) || keysPressed.contains(KeyCode.Z)) dy -= speed;
        if (keysPressed.contains(KeyCode.DOWN) || keysPressed.contains(KeyCode.S)) dy += speed;
        if (keysPressed.contains(KeyCode.LEFT) || keysPressed.contains(KeyCode.Q)) dx -= speed;
        if (keysPressed.contains(KeyCode.RIGHT) || keysPressed.contains(KeyCode.D)) dx += speed;

        if (dx != 0 || dy != 0) {
            hero.move(dx, dy); // ← appelle la méthode move() de ton characterView
        }
    }

    public void setHero(characterView hero){
            this.hero = hero;
    }

    public characterView setHero(){
        return this.hero;
    }


}
