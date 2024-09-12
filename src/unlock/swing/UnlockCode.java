package unlock.swing;

import unlock.UnlockFont;
import unlock.UnlockPlay;
import unlock.serialization.SerializableValue;

import java.util.Arrays;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class UnlockCode extends JFrame {

    private static UnlockWrongWayListener timer;

    private static Thread time;

    private JTextField user_field;

    private final static String[] ACCEPTED_VALUE = new String[]{"7", "11", "12", "713705"};

    public UnlockCode() {

        this.initComponents();
        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {

        timer = new UnlockWrongWayListener();
        time = new Thread(timer);
        time.start();

        UnlockWindow window = new UnlockWindow(50, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
            stopTimer();
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        user_field = new JTextField();
        user_field.setHorizontalAlignment(JTextField.CENTER);
        user_field.setPreferredSize(new java.awt.Dimension(418, 68));

        JButton save = new JButton("Sauvegarder");
        save.setFont(UnlockFont.getButtonFont());
        save.setBackground(new Color(139, 69, 19));
        save.setForeground(Color.WHITE);

        save.addActionListener(e -> {
            if (!user_field.getText().equals("")) {
                if (Arrays.asList(ACCEPTED_VALUE).contains(user_field.getText())) {
                    SerializableValue value = new SerializableValue(Integer.parseInt(user_field.getText()));
                    value.saveValue(UnlockPlay.getSerialFileFullPath());
                } else {
                    UnlockWrongWay error = new UnlockWrongWay("Cette valeur n'existe pas.");
                    error.setUndecorated(true);
                    error.setVisible(true);
                }
            }
        });

        JButton close = new JButton("Fermer");
        close.setFont(UnlockFont.getButtonFont());
        close.setBackground(new Color(139, 69, 19));
        close.setForeground(Color.WHITE);
        
        close.addActionListener(e -> {
           this.dispose();
           stopTimer();
        });

        JPanel center_panel = new JPanel();

        center_panel.setLayout(new BorderLayout());
        center_panel.add(new JLabel("Code à entrer :"), BorderLayout.CENTER);
        center_panel.add(user_field, BorderLayout.SOUTH);

        JPanel last_panel = new JPanel();

        last_panel.setLayout(new GridLayout(1, 2));
        last_panel.add(save);
        last_panel.add(close);

        window.add(center_panel, BorderLayout.CENTER);
        window.add(last_panel, BorderLayout.SOUTH);

        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }

    public static void stopTimer() {

        timer.kill();
        time.interrupt();
    }
}
