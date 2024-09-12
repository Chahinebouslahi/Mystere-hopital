package unlock.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

/**
 * Class which is a FX Application
 *
 * @author J.-C. BOISSON 2023/03
 *
 * @version 1.1
 */
public class IHMFx extends Application {

    /**
     * name of the FXML file
     */
    private final String FXML_FILE = "Unlock_FX.fxml";
    /**
     * directory where the FXML file(s) is(are)
     */
    private final String FXML_DIR = "fxml";

    private static Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;
    private double xInit;
    private double yInit;
    private boolean isResizing = false;

    private Timeline timeline = new Timeline();

    /**
     * Default constructor on for avoiding documentation warning
     */
    public IHMFx() {}

    /**
     * Procedure which is automatically called when a FX IHM is launched.
     *
     * @param primaryStage Stage already initialised by the FX ecosystem
     * @throws Exception Every exceptions that can occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(new FileInputStream(new File("src" + File.separator + "unlock" + File.separator + FXML_DIR + File.separator + FXML_FILE)));
        primaryStage.setTitle("Unlock : Mystère à l'Hôpital");

        Scene scene = new Scene(root, 700, 500);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        var bounds = Screen.getPrimary().getVisualBounds();

        //System.out.println(bounds);
        double x = primaryStage.getWidth() / 4;
        double y = bounds.getHeight() / 2 - primaryStage.getHeight() / 2;

        primaryStage.setX(x);
        primaryStage.setY(y);

        root.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame eCarte = new KeyFrame(
                Duration.millis(100),
                event -> {
                    scene.setOnMouseMoved(e -> {
                        xOffset = e.getSceneX();
                        yOffset = e.getSceneY();
                        xInit = primaryStage.getWidth();
                        yInit = primaryStage.getHeight();
                        isResizing = e.getSceneX() >= primaryStage.getWidth() - 10
                                && e.getSceneY() >= primaryStage.getHeight() - 10;
                        
                        if(isResizing) {
                            scene.setCursor(Cursor.SE_RESIZE);
                        } else {
                            scene.setCursor(Cursor.DEFAULT);
                        }
                    });
                }
        );

        timeline.getKeyFrames().addAll(eCarte);
        timeline.play();

        root.setOnMouseDragged(event -> {

            if (isResizing) {
                
                primaryStage.setWidth(xInit + (event.getSceneX() - xOffset));
                primaryStage.setHeight(yInit + (event.getSceneY() - yOffset));
            } 
        });

        stage = primaryStage;
        stage.getIcons().add(new Image(java.io.File.separator + "unlock" + java.io.File.separator + "images" + java.io.File.separator + "hospital.png"));
    }

    public static void getStageClosed(Stage primaryStage) {

        if (primaryStage != null) {
            primaryStage.close();
        }
    }

    public static Stage getStage() {

        return stage;
    }
}
