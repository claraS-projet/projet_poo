package controlleur;

import javafx.scene.layout.StackPane;
import modele.AddedForGUI.gameState;
import modele.Character.Doctor;
import modele.Character.MyCharacter;
import modele.Commands.MyCommands;
import modele.map.CodeDoor;
import modele.map.KeyDoor;
import modele.map.SimpleDoor;
import vue.exitView;
import vue.locationView;
import modele.AddedForGUI.gameState;
import vue.mainContainer;

import java.security.Key;
import java.util.ArrayList;

public class gameController {
    private gameState gameState;
    private StackPane root;
    private mainContainer currentView;
    private locationView currlocationView;
    MyCommands myCommands;

    public gameController(gameState gameState, StackPane root) {
        this.gameState = gameState;
        this.root = root;
        myCommands = new MyCommands();
    }

    public void startGame() {
        updateView();
    }

    public void updateView() {
        if (root.getChildren().size() > 1) {
            root.getChildren().remove(1, root.getChildren().size());
        }


        currentView = new mainContainer(this);
        currentView.prefWidthProperty().bind(root.widthProperty());
        currentView.prefHeightProperty().bind(root.heightProperty());

        root.getChildren().add(currentView);
    }

    public StackPane getRoot() {
        return root;
    }
    public gameState getGameState() {
        return gameState;
    }
    public mainContainer getCurrentView() {
        return currentView;
    }

    public void setCurrentlocView(locationView currentView) {
        this.currlocationView = currentView;
    }
    public locationView getCurrlocationView() {
        return currlocationView;
    }


    public void tryUseExit(exitView exit) {

        if (exit.getExit() instanceof SimpleDoor) {
            if (exit.getExit().canBeCrossed()) {
                System.out.println("Le héros utilise le vesseau normal vers " + exit.getExit().getneighbor());

                // Changer la Location dans le modèle
                gameState.goTo(exit.getExit().getneighbor().getName());
                myCommands.GO(exit.getExit().getneighbor().getName(), gameState.getHero());
                System.out.println(gameState.getHero().getPosition().getName());
                this.currentView.getUiPane().setLocname(gameState.getHero().getPosition().getName());

                // Recréer la nouvelle vue
                currentView.updateLocView(gameState.getCurrLocation(), this);
            }
        }
        else if (exit.getExit() instanceof KeyDoor) {
                if (exit.getExit().hero_unlock(this.gameState.getHero())) {
                    System.out.println("Le héros utilise le vesseau à clé vers " + exit.getExit().getneighbor());

                    // Changer la Location dans le modèle
                    gameState.goTo(exit.getExit().getneighbor().getName());
                    myCommands.GO(exit.getExit().getneighbor().getName(), gameState.getHero());
                    System.out.println(gameState.getHero().getPosition().getName());
                    this.currentView.getUiPane().setLocname(gameState.getHero().getPosition().getName());

                    // Recréer la nouvelle vue
                    currentView.updateLocView(gameState.getCurrLocation(), this);
                }
            }
            else if (exit.getExit() instanceof CodeDoor) {
                if (exit.getExit().hero_unlock(this.gameState.getHero())) {
                    System.out.println("Le héros utilise le vesseau à code vers " + exit.getExit().getneighbor());

                    // Changer la Location dans le modèle
                    gameState.goTo(exit.getExit().getneighbor().getName());
                    myCommands.GO(exit.getExit().getneighbor().getName(), gameState.getHero());
                    System.out.println(gameState.getHero().getPosition().getName());
                    this.currentView.getUiPane().setLocname(gameState.getHero().getPosition().getName());


                    // Recréer la nouvelle vue
                    currentView.updateLocView(gameState.getCurrLocation(), this);
                }
            }
    }

    public void setPDV(gameController ctrl){
        ArrayList<MyCharacter> chars = ctrl.gameState.getHero().getPosition().getCharacters();
        for(MyCharacter ch : chars){
            if (ch instanceof Doctor) {
                Doctor doc = (Doctor) ch;
                doc.mission(ctrl.gameState.getHero());
                this.currentView.getUiPane().setpdvlab(2);
            }
        }
    }

}
