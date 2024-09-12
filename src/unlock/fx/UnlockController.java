package unlock.fx;


import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

import unlock.UnlockPlay;
import unlock.serialization.SerializableValue;

/**
 * Class which manages the FXML file describing the IHM
 *
 * @author J.-C. BOISSON 2023/03
 *
 * @version 1.1
 */
public class UnlockController {

    /**
     * IHM top label
     */
    @FXML
    private Label top_label;

    @FXML
    private Button close_button;

    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox topHBox;

    private Timeline timeline = new Timeline();

    private int compteur = 0;

    private UnlockCard carte1;
    private UnlockCard carte2;
    private UnlockCard carte3;
    private UnlockCard carte4;
    private UnlockCard carte5;
    private UnlockCard carte6;
    private UnlockCard carte7;
    private UnlockCard carte8;
    private UnlockCard carte9;
    private UnlockCard carte10;
    private UnlockCard carte11;
    private UnlockCard carte12;
    private UnlockCard carte13;
    private UnlockCard carte14;

    /**
     * Default constructor for avoiding documentation warning
     */
    public UnlockController() {
    }

    /**
     * procedure which allows to access to all the IHM objects before the IHM is
     * visible
     */
    public void initialize() {

        top_label.setText("Unlock : Mystère à l'Hôpital");
        top_label.setTextFill(Color.WHITE);
        top_label.setFont(Font.font("Century Schoolbook", FontWeight.BOLD, 12));
        makeDraggable(topHBox);

        carte1 = new UnlockCard(1);
        carte1.addCerclesCarte(new CercleCliquable(2, 28, 117));
        carte1.addCerclesCarte(new CercleCliquable(3, 187, 138));

        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame eCarte = new KeyFrame(
                Duration.millis(100),
                event -> {
                    SerializableValue value = SerializableValue.loadValue(UnlockPlay.getSerialFileFullPath());

                    if (Integer.toString(value.getValue()).equals("-10")) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());
                        this.closeButtonClicked();
                    }

                    if (Integer.toString(value.getValue()).equals("2") && carte2 == null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte2 = new UnlockCard(2);
                        carte2.addCerclesCarte(new CercleCliquable(4, 188, 138));
                        rootPane.getChildren().add(carte2);
                    }

                    if (Integer.toString(value.getValue()).equals("3") && carte3 == null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte3 = new UnlockCard(3);
                        rootPane.getChildren().add(carte3);
                    }

                    if (Integer.toString(value.getValue()).equals("4") && carte4 == null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte4 = new UnlockCard(4);
                        carte2.setVisible(false);
                        rootPane.getChildren().add(carte4);
                    }

                    if (Integer.toString(value.getValue()).equals("5") && carte5 == null) {

                        carte5 = new UnlockCard(5);
                        carte7.setVisible(false);
                        rootPane.getChildren().add(carte5);
                    }

                    if (Integer.toString(value.getValue()).equals("7") && carte7 == null & carte3 == null & carte4 == null) {

                        value = new SerializableValue(Integer.parseInt("-9"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());
                    }

                    if (Integer.toString(value.getValue()).equals("7") && carte7 == null & carte3 != null & carte4 != null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte7 = new UnlockCard(7);
                        carte7.addCerclesCarte(new CercleCliquable(5, 17, 102));
                        carte3.setVisible(false);
                        carte4.setVisible(false);
                        rootPane.getChildren().add(carte7);
                    }

                    if (Integer.toString(value.getValue()).equals("713705") && carte6 == null && carte5 == null) {

                        value = new SerializableValue(Integer.parseInt("-9"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());
                    }

                    if (Integer.toString(value.getValue()).equals("713705") && carte6 == null && carte5 != null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte6 = new UnlockCard(6);
                        carte5.setVisible(false);
                        carte6.addCerclesCarte(new CercleCliquable(8, 117, 98));
                        rootPane.getChildren().add(carte6);
                    }

                    if (Integer.toString(value.getValue()).equals("8") && carte8 == null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte8 = new UnlockCard(8);
                        carte6.setVisible(false);
                        carte8.addCerclesCarte(new CercleCliquable(9, 179, 125));
                        rootPane.getChildren().add(carte8);
                    }

                    if (Integer.toString(value.getValue()).equals("9") && carte9 == null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte9 = new UnlockCard(9);
                        carte8.setVisible(false);
                        carte9.addCerclesCarte(new CercleCliquable(10, 126, 85));
                        carte9.addCerclesCarte(new CercleCliquable(15, 41, 110));
                        carte9.addCerclesCarte(new CercleCliquable(16, 159, 117));
                        rootPane.getChildren().add(carte9);
                    }

                    if (Integer.toString(value.getValue()).equals("11")) {

                        if (carte10 == null || carte11 == null || carte12 == null) {

                            value = new SerializableValue(Integer.parseInt("-9"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());
                        } else if(carte10.isVisible() && carte11.isVisible() && carte12.isVisible() && carte14 == null) {

                            value = new SerializableValue(Integer.parseInt("-5"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());

                            carte13 = new UnlockCard(13);
                            carte10.setVisible(false);
                            carte11.setVisible(false);
                            carte12.setVisible(false);
                            rootPane.getChildren().add(carte13);
                        }
                    }

                    if (Integer.toString(value.getValue()).equals("12")) {

                        if (carte10 == null || carte11 == null || carte12 == null) {

                            value = new SerializableValue(Integer.parseInt("-9"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());
                        } else if(carte10.isVisible() && carte11.isVisible() && carte12.isVisible() && carte13 == null) {

                            value = new SerializableValue(Integer.parseInt("-4"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());

                            carte14 = new UnlockCard(14);
                            carte10.setVisible(false);
                            carte11.setVisible(false);
                            carte12.setVisible(false);
                            rootPane.getChildren().add(carte14);
                        }
                    }

                    if (Integer.toString(value.getValue()).equals("10") && carte9 != null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte10 = new UnlockCard(10);
                        rootPane.getChildren().add(carte10);
                    }

                    if (Integer.toString(value.getValue()).equals("15") && carte9 != null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte11 = new UnlockCard(11);
                        rootPane.getChildren().add(carte11);
                    }

                    if (Integer.toString(value.getValue()).equals("16") && carte9 != null) {

                        value = new SerializableValue(Integer.parseInt("0"));
                        value.saveValue(UnlockPlay.getSerialFileFullPath());

                        carte12 = new UnlockCard(12);
                        rootPane.getChildren().add(carte12);
                    }

                    if (carte3 != null & carte4 != null) {

                        if (compteur == 0) {

                            value = new SerializableValue(Integer.parseInt("-8"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());

                            compteur++;
                        }
                    }

                    if (carte7 != null) {
                        if (!carte7.isVisible() & carte6 == null & compteur == 1) {

                            value = new SerializableValue(Integer.parseInt("-7"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());
                            compteur++;
                        }
                    }

                    if (carte10 != null && carte11 != null & carte12 != null) {
                        if (compteur == 2) {

                            value = new SerializableValue(Integer.parseInt("-6"));
                            value.saveValue(UnlockPlay.getSerialFileFullPath());
                            compteur++;
                        }
                    }

                    if (carte2 != null && carte3 != null) {
                        carte1.setVisible(false);
                    }

                    if (carte10 != null & carte11 != null & carte12 != null) {
                        carte9.setVisible(false);
                    }
                }
        );

        timeline.getKeyFrames().addAll(eCarte);
        timeline.play();

        rootPane.getChildren().add(carte1);
    }

    private void makeDraggable(HBox box) {

        box.setOnMousePressed(event -> {
            box.setOnMouseDragged(event1 -> {
                box.getScene().getWindow().setX(event1.getScreenX() - event.getSceneX());
                box.getScene().getWindow().setY(event1.getScreenY() - event.getSceneY());
            });
        });
    }

    @FXML
    private void closeButtonClicked() {

        IHMFx.getStageClosed(IHMFx.getStage());
    }

    @FXML
    private void minifyButtonClicked() {

        IHMFx.getStage().setIconified(true);
    }
}
