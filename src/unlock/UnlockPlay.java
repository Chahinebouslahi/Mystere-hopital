package unlock;

import unlock.swing.UnlockWindow;
import unlock.swing.UnlockMusicPlayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public final class UnlockPlay extends JFrame {

    private static UnlockMusicPlayer player;

    private static Thread music;

    /**
     * default value for the serial file directory
     */
    private static final String SERIAL_FILE_DIRECTORY = "serial";
    /**
     * default value for the serial file name
     */
    private static final String SERIAL_FILE_NAME = "file.ser";
    /**
     * path where the serial file(s) will be written
     */
    private static String serial_file_full_path;

    public UnlockPlay() {

        this.initComponents();
        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {

        serial_file_full_path = "src" + java.io.File.separator + "unlock" + java.io.File.separator + SERIAL_FILE_DIRECTORY + java.io.File.separator + SERIAL_FILE_NAME;

        player = new UnlockMusicPlayer("src" + java.io.File.separator + "unlock" + java.io.File.separator + "sons" + java.io.File.separator + "son.mp3");
        music = new Thread(player);
        music.start();

        UnlockWindow window = new UnlockWindow(50, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
            stopPlayer();
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        JLabel title = new JLabel("Unlock : Mystère à l'Hôpital");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(UnlockFont.getTitleFont());

        JLabel subtitle = new JLabel("Prêt à commencer votre aventure ?");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(UnlockFont.getSubtitleFont());

        JButton play = new JButton("Commencer");
        play.setFont(UnlockFont.getButtonFont());
        play.setBackground(new Color(139, 69, 19));
        play.setForeground(Color.WHITE);

        play.addActionListener(e -> handlePlayButton(e));

        JPanel center_panel = new JPanel();
        center_panel.setLayout(new GridLayout(2, 1));
        center_panel.add(title);
        center_panel.add(subtitle);

        JPanel last_panel = new JPanel();

        last_panel.add(play);

        window.add(center_panel, BorderLayout.CENTER);
        window.add(last_panel, BorderLayout.SOUTH);

        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }

    private void handlePlayButton(ActionEvent e) {

        UnlockIntro unlock = new UnlockIntro();
        unlock.setUndecorated(true);
        unlock.setVisible(true);
        
        this.dispose();
    }
    
    public static void stopPlayer() {

        player.kill();
        music.interrupt();
    }

    /**
     * Getter to the path where the serial file (or will be)
     *
     * @return the value of the full path to the serial file
     */
    public static String getSerialFileFullPath() {
        return serial_file_full_path;
    }
}
