package vue;

import controlleur.gameController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ui_Vbox extends VBox {

    gameController ctrl;
    Label pos;
    Label ammoLabel;
    Label hpLabel;

    private int pdv;
    private int ammo;
    private String locname;

    public ui_Vbox(gameController controller) {

        super();
        ctrl = controller;
        pdv = ctrl.getGameState().getHero().getHP();
        locname = controller.getGameState().getHero().getPosition().getName();
        hpLabel = new Label("Points de vie : " + controller.getGameState().getHero().getHP());
        hpLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        this.getChildren().add(hpLabel);
        ammoLabel = new Label("Munitions : " + controller.getGameState().getHero().getH_bow().getNbArrows());
        ammoLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        this.getChildren().add(ammoLabel);
        pos = new Label("Position : " + controller.getGameState().getHero().getPosition().getName());
        pos.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        this.getChildren().add(pos);

    }

    public void setLocname(String newloc){
        locname = "position : " + newloc;
        pos.setText(locname);
    }

    public void setpdvlab (int val){
        pdv += val;
        hpLabel.setText("Points de vie : " + pdv);
    }

}
