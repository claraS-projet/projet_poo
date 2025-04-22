package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modele.AddedForGUI.gameState;
import controlleur.gameController;
import utils.game_Init;

public class mainApp extends Application {

    public int SCENE_WIDTH;
    public int SCENE_HEIGHT;

    private StackPane root;
    private Scene scene;
    private gameState gameState;

    @Override
    public void start(Stage primaryStage) {

        gameState = game_Init.initialization();

        root = new StackPane();

        scene = new Scene(root, 800, 600);
        SCENE_WIDTH = 800;
        SCENE_HEIGHT = 600;

        // 🌌 Fond spatial permanent
        Image spaceBg = new Image("gle/space_bg6.jpg");
        if (spaceBg.isError()) {
            System.out.println("⚠️ Erreur de chargement image !");
        }

        ImageView bgView = new ImageView(spaceBg);
        bgView.setFitWidth(800);
        bgView.setFitHeight(600);

        bgView.setPreserveRatio(false); // ou true si tu veux garder les proportions
        bgView.setSmooth(true);

        // Lier dynamiquement aux dimensions du parent
        bgView.fitWidthProperty().bind(scene.widthProperty());
        bgView.fitHeightProperty().bind(scene.heightProperty());

        root.getChildren().add(bgView);
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        // Page d'accueil avec bouton
        startScreenView startScreen = new startScreenView(() -> startGame());
        root.getChildren().add(startScreen);


        primaryStage.setTitle("Exploration Galactique");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame() {
        gameController controller = new gameController(gameState, root);
        controller.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
