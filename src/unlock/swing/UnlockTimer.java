package unlock.swing;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;

import java.awt.Color;
import unlock.UnlockPlay;
import unlock.serialization.SerializableValue;

public class UnlockTimer implements Runnable {

    private int temps;
    private final JLabel label;
    private final JFrame frame;
    private Timer timer;
    private int compteur = 0;

    public UnlockTimer(int temps, JLabel label, JFrame frame) {
        this.temps = temps;
        this.label = label;
        this.frame = frame;
    }

    @Override
    public void run() {
        timer = new Timer(1000, event
                -> {
            temps--;

            int min = (int) (temps / 60);
            int reste = temps - (min * 60);

            if (temps > 0) {
                if (temps >= 600) {

                    if (reste < 10) {

                        label.setText(Integer.toString(min) + ":0" + Integer.toString(reste));
                    } else {

                        label.setText(Integer.toString(min) + ":" + Integer.toString(reste));
                    }
                } else if (temps >= 60 && temps < 600) {

                    if (reste < 10) {

                        label.setText("0" + Integer.toString(min) + ":0" + Integer.toString(reste));
                    } else {

                        label.setText("0" + Integer.toString(min) + ":" + Integer.toString(reste));
                    }
                } else if (temps < 60 && temps >= 10) {
                    label.setText("00:" + Integer.toString(temps));
                    label.setForeground(Color.RED);
                } else {
                    label.setText("00:0" + Integer.toString(temps));
                    label.setForeground(Color.RED);
                }
            } else if (temps == 0) {

                label.setText(Integer.toString(temps));
                timer.stop();
                if (frame instanceof IHMSwing) {
                    IHMSwing.handleTimeEnd();
                    frame.dispose();
                }
            }

            SerializableValue value = SerializableValue.loadValue(UnlockPlay.getSerialFileFullPath());

            switch (Integer.toString(value.getValue())) {
                case "-5" -> {

                    Timer t = new Timer(5000, e -> {

                        if (compteur == 0) {
                            UnlockBadEnd badending = new UnlockBadEnd(true);
                            badending.setUndecorated(true);
                            badending.setVisible(true);

                            if (frame instanceof IHMSwing) {
                                IHMSwing.handleFinalEnd();
                                frame.dispose();
                            }

                            compteur++;
                        }
                    });

                    t.setRepeats(false);
                    t.start();

                }
                case "-4" -> {
                    Timer t = new Timer(5000, e -> {
                        if (compteur == 0) {
                            UnlockGoodEnd goodending = new UnlockGoodEnd(true);
                            goodending.setUndecorated(true);
                            goodending.setVisible(true);

                            if (frame instanceof IHMSwing) {
                                IHMSwing.handleFinalEnd();
                                frame.dispose();
                            }
                            
                            compteur++;
                        }
                    });

                    t.setRepeats(false);
                    t.start();
                }
            }
        });

        timer.setRepeats(true);
        timer.start();
    }

    public int getTemps() {

        return this.temps;
    }

    public JLabel getLabel() {

        return this.label;
    }

    public Timer getTimer() {

        return this.timer;
    }

    public void kill() {

        timer.stop();
    }
}
