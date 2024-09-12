package unlock.fx;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import unlock.UnlockPlay;
import unlock.serialization.SerializableValue;

public class UnlockCard extends ImageView {

    private static final String IMAGE_DIRECTORY = "images";
    private static final String IMAGE_PREFIX = "carte_";
    private static final String IMAGE_SUFFIX = ".png";
    private static final char SEPARATOR_CHAR = File.separatorChar;
    private static final Integer IMAGE_HEIGHT = 300;

    private final int numero;
    private double dragX, dragY;

    private final ArrayList<CercleCliquable> cerclesCliquable;
    public int status = -1;

    public UnlockCard(int numero) {

        this.numero = numero;

        String chemin = "src" + SEPARATOR_CHAR;
        chemin += "unlock";
        chemin += SEPARATOR_CHAR;
        chemin += IMAGE_DIRECTORY;
        chemin += SEPARATOR_CHAR;
        chemin += IMAGE_PREFIX;
        chemin += this.numero;
        chemin += IMAGE_SUFFIX;

        File file = new File(chemin);

        setImage(new Image(file.toURI().toString()));
        setPreserveRatio(true);
        setFitHeight(IMAGE_HEIGHT);

        this.setOnMousePressed(e -> handleMousePressed(e));
        this.setOnMouseDragged(e -> handleMouseDragged(e));
        this.setOnMouseClicked(e -> handleMouseClicked(e));

        cerclesCliquable = new ArrayList<>();
        
        this.setLayoutX(100);
        this.setLayoutY(100);
    }

    public int getNumero() {
        return numero;
    }

    protected void handleMousePressed(MouseEvent e) {

        if (e.getButton() == MouseButton.PRIMARY) {
            this.dragX = e.getScreenX() - this.getTranslateX();
            this.dragY = e.getScreenY() - this.getTranslateY();

            handleCircleEvent(e);
        }
    }

    protected void handleMouseDragged(MouseEvent e) {

        if (e.getButton() == MouseButton.PRIMARY) {
            this.translateXProperty().set(e.getScreenX() - dragX);
            this.translateYProperty().set(e.getScreenY() - dragY);
        }
    }

    protected void handleMouseClicked(MouseEvent e) {

        if (e.getButton() == MouseButton.PRIMARY) {
            this.toFront();
        }

        if (e.getButton() == MouseButton.SECONDARY) {
            this.setRotate(getRotate() + 45);
        }
    }

    protected void addCerclesCarte(CercleCliquable cercle) {

        this.cerclesCliquable.add(cercle);
    }

    public ArrayList getCerclesCliquable() {

        return this.cerclesCliquable;
    }

    public void handleCircleEvent(MouseEvent e) {
        
        if (this.getCerclesCliquable() != null) {

            for (int i = 0; i < this.getCerclesCliquable().size(); i++) {

                CercleCliquable cercle = (CercleCliquable) this.getCerclesCliquable().get(i);

                if (cercle.isInside(e.getX(), e.getY())) {
                    SerializableValue value = new SerializableValue(cercle.getNumero());
                    value.saveValue(UnlockPlay.getSerialFileFullPath());
                }
            }
        }
    }
}
