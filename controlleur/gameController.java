package controlleur;

import javafx.scene.layout.StackPane;
import modele.AddedForGUI.gameState;
import vue.locationView;
import modele.AddedForGUI.gameState;
import vue.mainContainer;

public class gameController {
    private gameState gameState;
    private StackPane root;
    private mainContainer currentView;
    private locationView currlocationView;

    public gameController(gameState gameState, StackPane root) {
        this.gameState = gameState;
        this.root = root;
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
}
