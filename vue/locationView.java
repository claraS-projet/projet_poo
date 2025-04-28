package vue;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import modele.Character.Doctor;
import modele.Character.Guide;
import modele.Character.Hero;
import modele.Character.MyCharacter;
import modele.map.Exit;
import modele.map.Location;
import modele.map.KeyDoor;
import modele.map.CodeDoor;
import controlleur.gameController;
import modele.AddedForGUI.gameState;
import javafx.animation.FadeTransition;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class locationView extends Pane {
    //private gameState gameState;
    private gameController controller;
    private characterView hero;
    private Location location;
    private final Set<KeyCode> keysPressed = new HashSet<>();
    private guideView guide;
    private doctorView doctor;
    private boolean keyTaken = false;

    private exitView currentNearbyExitView;

    public locationView(Location loc, gameController controller) {
        super();

        this.location = loc;
        this.controller = controller;

        this.prefWidthProperty().bind(controller.getRoot().widthProperty());
        this.prefHeightProperty().bind(controller.getRoot().heightProperty());

        drawLocation(controller);

        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                removeExits();
                drawExits(controller);
            }
        });


        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) {
                removeHero();
                drawHero(controller);
            }
        });



        this.setFocusTraversable(true); // Obligatoire pour capter les touches
        this.setOnKeyPressed(e -> {
            System.out.println("Touche appuyée : " + e.getCode());
            keysPressed.add(e.getCode());

            if (e.getCode() == KeyCode.O) {
                checkNearbyExits(); // Toujours vérifier d'abord
                if (currentNearbyExitView != null) {
                    System.out.println("hello");
                    System.out.println(currentNearbyExitView.path);
                    controller.tryUseExit(currentNearbyExitView);
                } else {
                    System.out.println("Aucun exit proche !");
                }
            }
            if (e.getCode() == KeyCode.Y) {
                checkNearbyExits(); // Toujours vérifier d'abord
                if (!keyTaken) {
                    if (guide != null) {
                        System.out.println("hello guide");
                        this.guide.guideInteraction();
                        //setonKeyPforGuide(KeyCode.Y);
                        this.guide.getGuide().mission(this.controller.getGameState().getHero());
                        keyTaken = true;
                    } else {
                        System.out.println("Aucun guide proche !");
                    }

            }
            else{
                    if (guide != null) {
                        this.guide.guideInteraction();
                    } else {
                        System.out.println("Aucun guide proche !");
                    }
                }
            }
            if (e.getCode() == KeyCode.H) {
                checkNearbyExits(); // Toujours vérifier d'abord
                //this.doctorInteraction();
                this.controller.setPDV(controller);
                System.out.println("points de vie hero : " + this.controller.getGameState().getHero().getHP());
            }

            handleMovement();
        });


        this.setOnKeyReleased(e -> {
            keysPressed.remove(e.getCode());
        });

        ArrayList<MyCharacter> mycharacters = this.location.getCharacters();
        for (MyCharacter charac : mycharacters) {
            if (charac instanceof Guide) {
                drawGuide(controller);
                System.out.println("je dessine le guide ");
            }
            if (charac instanceof Doctor) {
                drawDoctor(controller);
                System.out.println("je dessine le docteur ");
            }
        }

    }

    public void drawLocation(gameController controller) {
        String pos = controller.getGameState().getCurrLocation().getName();
        Image locBg;
        switch (pos) {
            case "EARTH" :
                locBg = new Image("/locations/planet03.png");
                break;
            case "MOON" :
                locBg = new Image("/locations/planet05.png");
                break;
            case "MARS" :
                locBg = new Image("/locations/planet08.png");
                break;
            case "URANUS" :
                locBg = new Image("/locations/planet04.png");
                break;
            case "NEPTUNE" :
                locBg = new Image("/locations/planet07.png");
                break;
            case "SATURN" :
                locBg = new Image("/locations/planet06.png");
                break;
            case "VENUS" :
                locBg = new Image("/locations/planet02.png");
                break;
            case "JUPITER" :
                locBg = new Image("/locations/planet01.png");
                break;
            default:
            System.out.println("LE HERO N'EST PAS POSITIONNE");
            return;
        }
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
            //vérifier la proximité des exits ---
            checkNearbyExits();
        }

    }

    private void checkNearbyExits() {
        exitView nearestExit = null;
        guideView nearguide = null;
        double nearestDistance = Double.MAX_VALUE;
        double nearestguidedistance = Double.MAX_VALUE;
        doctorView neardoctor = null;
        double nearestdoctordistance = Double.MAX_VALUE;

        // Récupérer la position du héros en coordonnées de scène
        Bounds heroBounds = hero.localToScene(hero.getBoundsInLocal());
        double heroCenterX = heroBounds.getMinX() + heroBounds.getWidth() / 2;
        double heroCenterY = heroBounds.getMinY() + heroBounds.getHeight() / 2;

        for (var node : this.getChildren()) {
            if (node instanceof exitView exit) {
                // Récupérer la position de l'exit en coordonnées de scène
                Bounds exitBounds = exit.localToScene(exit.getBoundsInLocal());
                double exitCenterX = exitBounds.getMinX() + exitBounds.getWidth() / 2;
                double exitCenterY = exitBounds.getMinY() + exitBounds.getHeight() / 2;

                // Calcul correct de la distance
                double dx = heroCenterX - exitCenterX;
                double dy = heroCenterY - exitCenterY;
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Debug
                System.out.println("Distance entre le héros et l'exit " + exit.getExit().getName() + " : " + distance);

                if (distance < 80) { // Seuil de proximité
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestExit = exit;
                    }
                }
            }
            if (node instanceof guideView guide) {
                // Récupérer la position de l'exit en coordonnées de scène
                Bounds guideBounds = guide.localToScene(guide.getBoundsInLocal());
                double guideCenterX = guideBounds.getMinX() + guideBounds.getWidth() / 2;
                double guideCenterY = guideBounds.getMinY() + guideBounds.getHeight() / 2;

                // Calcul correct de la distance
                double dx = heroCenterX - guideCenterX;
                double dy = heroCenterY - guideCenterY;
                double distance2 = Math.sqrt(dx * dx + dy * dy);

                // Debug
                System.out.println("Distance entre le héros et le guide : " + distance2);

                if (distance2 < 80) { // Seuil de proximité
                    if (distance2 < nearestguidedistance) {
                        nearestguidedistance = distance2;
                        nearguide = guide;
                    }
                }
            }
            if (node instanceof doctorView doctor) {
                // Récupérer la position de l'exit en coordonnées de scène
                Bounds doctorBounds = doctor.localToScene(doctor.getBoundsInLocal());
                double doctorCenterX = doctorBounds.getMinX() + doctorBounds.getWidth() / 2;
                double doctorCenterY = doctorBounds.getMinY() + doctorBounds.getHeight() / 2;

                // Calcul correct de la distance
                double dx = heroCenterX - doctorCenterX;
                double dy = heroCenterY - doctorCenterY;
                double distance3 = Math.sqrt(dx * dx + dy * dy);

                // Debug
                System.out.println("Distance entre le héros et le docteur : " + distance3);
                if (distance3 < 80) { // Seuil de proximité
                    if (distance3 < nearestdoctordistance) {
                        nearestdoctordistance = distance3;
                        neardoctor = doctor;
                    }
                }

            }
        }

        if (nearestExit != null) {
            if (currentNearbyExitView != nearestExit) {
                currentNearbyExitView = nearestExit;
                // showExitMessage(nearestExit);
            }
        } else {
            currentNearbyExitView = null;
        }
        if (nearguide != null) {
            if (guide != nearguide) {
                guide = nearguide;
                // showExitMessage(nearestExit);
            }
        } else {
            guide = null;
        }
        if (neardoctor != null) {
            if (doctor != neardoctor) {
                doctor = neardoctor;
                // showExitMessage(nearestExit);
            }
        } else {
            doctor = null;
        }
    }

    public Location getLocation(){
        return this.location;
    }

    public void setHero(characterView hero){
            this.hero = hero;
    }


    public characterView setHero(){
        return this.hero;
    }


    public void drawExits(gameController controller) {
        Map<String, Exit> exits = controller.getGameState().getCurrLocation().getExits();

        int exitCount = exits.size();
        if (exitCount == 0) return;

        double spacing = this.getWidth() / (exitCount + 1);
        int i = 0;

        for (Map.Entry<String, Exit> entry : exits.entrySet()) {
            String name = entry.getKey(); // Le nom de l'exit, ex: "KD_Earth2Mars"
            Exit exit = entry.getValue();

            // Choix de l’image en fonction du type de porte
            String imagePath = "/items/exits/craft_racer_NW.png";
            if (exit instanceof KeyDoor) {
                imagePath = "/items/exits/craft_speederA_NW.png";
            } else if (exit instanceof CodeDoor) {
                imagePath = "/items/exits/craft_speederB_NW.png";
            }

            // Créer la vue du vaisseau
            exitView view = new exitView(exit, imagePath);

            // Positionner la sortie en haut de l’écran, espacée horizontalement
            double x = spacing * (i + 1) - 32; // 32 = moitié largeur du sprite (64)
            double y = 20;

            view.setLayoutX(x);
            view.setLayoutY(y);

            this.getChildren().add(view);
            i++;
        }
    }

    public void removeExits() {
        this.getChildren().removeIf(node -> node instanceof exitView);
    }

    public void removeHero(){
        this.getChildren().removeIf(node -> node instanceof characterView);
    }

    public void showExitMessage(exitView exit) {
        Platform.runLater(() -> {
            if (exit.getScene() == null) {
                System.out.println("exitView n'a pas encore de Scene attachée !");
                return;
            }

            String message = "Il y a une embarcation vers une autre planète. ";

            if (exit.getExit() instanceof KeyDoor) {
                message += "Une clé est nécessaire.";
            } else if (exit.getExit() instanceof CodeDoor) {
                message += "Un code d'accès est requis.";
            } else {
                message += "Appuie sur 'O' pour embarquer.";
            }

            // Création du Label
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

            // Création du Popup
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.setAutoFix(true);
            popup.setAutoHide(false);
            popup.setHideOnEscape(false);

            // Calcul de la position
            Bounds exitBounds = exit.localToScene(exit.getBoundsInLocal());
            Window window = exit.getScene().getWindow();

            double exitCenterX = exitBounds.getMinX() + (exitBounds.getWidth() / 2);
            double popupWidth = 160;
            double popupX = window.getX() + exit.getScene().getX() + exitCenterX - popupWidth / 2;
            double popupY = window.getY() + exit.getScene().getY() + exitBounds.getMinY() - 50;

            popup.show(window, popupX, popupY);

            // Animation d'apparition
            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), label);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

            // Fermeture automatique après 3 secondes
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> popup.hide());
            pause.play();
        });
    }



    public void drawGuide(gameController controller) {
        guideView guide = new guideView(
                controller.getCurrlocationView(), 40, 400,
                "/characters/guide/alien_SE.png"
        );
        this.getChildren().add(guide);
        guide.bindToBottomCenter(this);

        //guide.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

    }
    public void setGuide(guideView g){
        this.guide = g;
    }

    public void showGuideExitMessage() {
        if (this.guide != null) {
            String message = "I'm here to help you, press 'Y' to discuss ";


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
            Bounds exitBounds = guide.localToScene(guide.getBoundsInLocal());
            Window window = guide.getScene().getWindow();

            double exitCenterX = exitBounds.getMinX() + (exitBounds.getWidth() / 2);
            double popupWidth = 160; // même que MaxWidth du label
            double popupX = window.getX() + guide.getScene().getX() + exitCenterX - popupWidth / 2;

            double popupY = window.getY() + guide.getScene().getY() + exitBounds.getMinY() - 50; // au-dessus de l'exit

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


    public void setonKeyPforGuide(KeyCode x) {
        this.setOnKeyPressed(e -> {
            System.out.println("Touche appuyée : " + e.getCode());
            if (e.getCode() == x) {
                this.guide.getGuide().mission(this.controller.getGameState().getHero());
            }
        });
    }

    public void drawDoctor(gameController controller) {
            System.out.println("je suis entré dans drawdoctor ");
            doctorView doc = new doctorView(
                    controller.getCurrlocationView(), 40, 400,
                    "/characters/doctor/doctor1.png"
            );
            this.getChildren().add(doc);
            doc.bindToBottomCenter(this);

            //doc.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

    }
    public void doctorInteraction() {
        String message = "Je suis là pour te donner quelques points de vie si besoin, appuie sur H pour en avoir";

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

        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.setAutoFix(true);
        popup.setAutoHide(false);
        popup.setHideOnEscape(false);

        Bounds doctorBounds = this.localToScene(this.getBoundsInLocal());
        Window window = this.getScene().getWindow();

        double doctorCenterX = doctorBounds.getMinX() + (doctorBounds.getWidth() / 2);
        double popupWidth = 160;
        double popupX = window.getX() + this.getScene().getX() + doctorCenterX - popupWidth / 2;
        double popupY = window.getY() + this.getScene().getY() + doctorBounds.getMinY() - 50;

        popup.show(window, popupX, popupY);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), label);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> popup.hide());
        pause.play();
    }

    public void setDoctor(doctorView doc){
        this.doctor = doc;
    }




}
