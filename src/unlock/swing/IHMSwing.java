package unlock.swing;

import unlock.UnlockPlay;
import unlock.UnlockFont;
import unlock.serialization.SerializableValue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * Class which is an IHM SWING
 *
 * @author J.-C. BOISSON 2022/02
 *
 * @version 1.0
 */
public final class IHMSwing extends JFrame implements Runnable {

    private static Thread time;

    private static UnlockTimer timer;

    private final JLabel timelabel = new JLabel();

    public IHMSwing() {

        this.initComponents();

        var toolkit = getToolkit();
        var dimensions = toolkit.getScreenSize();

        int x = (int) (dimensions.getWidth() / 2 + getWidth() / 4);
        int y = (int) (dimensions.getHeight() / 4 - getHeight() / 2);

        this.setLocation(x, y);

        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {

        timelabel.setFont(UnlockFont.getTitleFont());
        timelabel.setHorizontalAlignment(SwingConstants.CENTER);

        timer = new UnlockTimer(301, timelabel, this);
        time = new Thread(timer);
        time.start();

        UnlockWindow window = new UnlockWindow(250, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
            stopTimer();
            UnlockPlay.stopPlayer();
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        JPanel central_panel = new JPanel();
        central_panel.setLayout(new GridLayout(3, 3));

        JButton hint = new JButton("Indice");
        hint.setFont(UnlockFont.getButtonFont());
        hint.setBackground(new Color(139, 69, 19));
        hint.setForeground(Color.WHITE);

        hint.addActionListener(e -> {

            SerializableValue value = SerializableValue.loadValue(UnlockPlay.getSerialFileFullPath());

            switch (Integer.toString(value.getValue())) {
                case "-8" -> {
                    UnlockHint hints = new UnlockHint("L'addition est la seule solution.");
                    hints.setUndecorated(true);
                    hints.setVisible(true);
                }
                case "-7" -> {
                    UnlockHint hints = new UnlockHint("Le mot \"SOLEIL\" sur une calculatrice.");
                    hints.setUndecorated(true);
                    hints.setVisible(true);
                }
                case "-6" -> {
                    UnlockHint hints = new UnlockHint("L'un de vos indices est la clé.");
                    hints.setUndecorated(true);
                    hints.setVisible(true);
                }
                default -> {
                    UnlockWrongWay error = new UnlockWrongWay("Vous ne pouvez pas encore voir cet indice.");
                    error.setUndecorated(true);
                    error.setVisible(true);
                }
            }
        });

        JButton code = new JButton("Entrez un code");
        code.addActionListener(e -> {

            UnlockCode entry = new UnlockCode();
            entry.setUndecorated(true);
            entry.setVisible(true);

        });

        code.setFont(UnlockFont.getButtonFont());
        code.setBackground(new Color(139, 69, 19));
        code.setForeground(Color.WHITE);

        central_panel.add(new JPanel());
        central_panel.add(timelabel);
        central_panel.add(new JPanel());
        central_panel.add(hint);
        central_panel.add(new JPanel());
        central_panel.add(code);
        central_panel.add(new JPanel());
        central_panel.add(new JPanel());
        central_panel.add(new JPanel());

        JPanel last_panel = new JPanel();

        JButton abandon = new JButton("Abandonner ?");

        abandon.setFont(UnlockFont.getButtonFont());
        abandon.setBackground(new Color(139, 69, 19));
        abandon.setForeground(Color.WHITE);

        abandon.addActionListener(e -> {

            handleEndGame();
            UnlockPlay.stopPlayer();
        });

        last_panel.add(abandon, BorderLayout.CENTER);

        window.add(central_panel, BorderLayout.CENTER);
        window.add(last_panel, BorderLayout.SOUTH);

        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }

    public void saveValue(String val) {

        SerializableValue value = new SerializableValue(Integer.parseInt(val));
        value.saveValue(UnlockPlay.getSerialFileFullPath());
    }

    public void handleEndGame() {

        this.saveValue("-10");

        UnlockBadEnd badend = new UnlockBadEnd(true);
        badend.setUndecorated(true);
        badend.setVisible(true);

        this.dispose();

        stopTimer();
    }

    public static void handleTimeEnd() {

        SerializableValue value = new SerializableValue(Integer.parseInt("-10"));
        value.saveValue(UnlockPlay.getSerialFileFullPath());

        UnlockTimeEnd badend = new UnlockTimeEnd(true);
        badend.setUndecorated(true);
        badend.setVisible(true);
        
        UnlockPlay.stopPlayer();

        stopTimer();
    }

    public static void handleFinalEnd() {
        
        SerializableValue value = new SerializableValue(Integer.parseInt("-10"));
        value.saveValue(UnlockPlay.getSerialFileFullPath());
        
        UnlockPlay.stopPlayer();
        
        stopTimer();
    }

    public static void stopTimer() {

        timer.kill();
        time.interrupt();
    }

    @Override
    public void run() {

        this.setUndecorated(true);
        this.setVisible(true);
    }
}
