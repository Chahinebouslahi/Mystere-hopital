package unlock.swing;

import unlock.UnlockPlay;
import unlock.serialization.SerializableValue;

import javax.swing.Timer;

public class UnlockWrongWayListener implements Runnable {

    private Timer timer;

    public UnlockWrongWayListener() {
    }

    @Override
    public void run() {
        timer = new Timer(100, event
                -> {
            SerializableValue value = SerializableValue.loadValue(UnlockPlay.getSerialFileFullPath());

            if (Integer.toString(value.getValue()).equals("-9")) {

                value = new SerializableValue(Integer.parseInt("0"));
                value.saveValue(UnlockPlay.getSerialFileFullPath());

                UnlockWrongWay wrong = new UnlockWrongWay("Vous ne pouvez pas ouvrir cette carte car vous n'êtes pas au bon endroit de l'histoire.");
                wrong.setUndecorated(true);
                wrong.setVisible(true);
            }
        });

        timer.setRepeats(true);
        timer.start();
    }
    
    public void kill() {
        timer.stop();
    }
}
