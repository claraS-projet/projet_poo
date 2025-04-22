package vue;

import controlleur.gameController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ui_Vbox extends VBox {

    private int pdv;
    private int ammo;

    public ui_Vbox(gameController controller) {
        super();
        Label hpLabel = new Label("Points de vie : " + controller.getGameState().getHero().getHP());
        hpLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        this.getChildren().add(hpLabel);
        Label ammoLabel = new Label("Munitions : " + controller.getGameState().getHero().getH_bow().getNbArrows());
        ammoLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        this.getChildren().add(ammoLabel);

    }
}
