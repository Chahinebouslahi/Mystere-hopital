package unlock.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import unlock.UnlockFont;

public final class UnlockBadEnd extends JFrame {

    private static UnlockMusicPlayer player;

    private static Thread music;

    public UnlockBadEnd(boolean song) {

        this.initComponents(song);
        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents(boolean song) {

        if (song) {
            player = new UnlockMusicPlayer("src" + java.io.File.separator + "unlock" + java.io.File.separator + "sons" + java.io.File.separator + "lose.mp3");
            music = new Thread(player);
            music.start();
        }

        UnlockWindow window = new UnlockWindow(50, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
            
            if(song) {
                stopPlayer();
            }
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        JLabel title = new JLabel("Unlock : Mystère à l'Hôpital");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(UnlockFont.getTitleFont());

        JLabel subtitle = new JLabel("Vous avez perdu...");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(UnlockFont.getSubtitleFont());

        JLabel fin = new JLabel("Vous resterez à jamais au sein de l'hôpital...");
        fin.setHorizontalAlignment(SwingConstants.CENTER);
        fin.setFont(UnlockFont.setFont("Century Schoolbook", Font.BOLD, 15));

        JPanel center_panel = new JPanel();
        center_panel.setLayout(new GridLayout(3, 1));
        center_panel.add(title);
        center_panel.add(subtitle);
        center_panel.add(fin);

        window.add(center_panel, BorderLayout.CENTER);

        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }

    public static void stopPlayer() {

        player.kill();
        music.interrupt();
    }
}
