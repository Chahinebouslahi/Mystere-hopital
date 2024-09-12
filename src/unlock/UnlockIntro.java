package unlock;

import unlock.swing.UnlockWindow;
import unlock.fx.IHMFx;
import unlock.swing.IHMSwing;

import javafx.application.Application;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public final class UnlockIntro extends JFrame {

    private static Thread swing_thread;
    private static Thread fx_thread;

    public UnlockIntro() {

        this.initComponents();
        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {

        UnlockWindow window = new UnlockWindow(50, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
            UnlockPlay.stopPlayer();
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        JLabel title = new JLabel("Unlock : Mystère à l'Hôpital");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(UnlockFont.getTitleFont());

        JLabel subtitle = new JLabel("Introduction");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(UnlockFont.setFont("Century Schoolbook", Font.ITALIC, 16));

        String introString = """
                             <html><body><h2>Introduction</h2><p style='width: 250px;'>Vous entrez dans un h\u00f4pital en tant que visiteur, vous souhaitiez rendre visite \u00e0 votre
                             grand-p\u00e8re mais vous vous rendez vite compte qu'il y a quelque chose d'\u00e9trange qui se
                             passe. Des bruits \u00e9tranges r\u00e9sonnent dans les couloirs et des objets semblent avoir \u00e9t\u00e9
                             d\u00e9plac\u00e9s de part et autre de l'h\u00f4pital. Alors que vous explorez l'h\u00f4pital, vous d\u00e9couvrez
                             d'\u00e9trange choses qui vous perturbent.</p></body></html>
                             """;

        JLabel intro = new JLabel(introString);
        intro.setHorizontalAlignment(SwingConstants.CENTER);
        intro.setFont(UnlockFont.setFont("Century Schoolbook", Font.ITALIC, 14));

        JButton start = new JButton("C'est parti !");
        start.setFont(UnlockFont.getButtonFont());
        start.setBackground(new Color(139, 69, 19));
        start.setForeground(Color.WHITE);

        start.addActionListener(e -> handleStartButton(e));

        JPanel center_panel = new JPanel();

        center_panel.setLayout(new BorderLayout());

        center_panel.add(title, BorderLayout.NORTH);
        center_panel.add(intro, BorderLayout.CENTER);
        center_panel.add(new JPanel(), BorderLayout.SOUTH);

        JPanel last_panel = new JPanel();

        last_panel.add(start);

        window.add(center_panel, BorderLayout.CENTER);
        window.add(last_panel, BorderLayout.SOUTH);

        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }

    protected void handleStartButton(ActionEvent e) {

        swing_thread = new Thread(new IHMSwing());
        fx_thread = new Thread(() -> Application.launch(IHMFx.class, new String[1]));

        swing_thread.start();
        fx_thread.start();

        this.dispose();
    }
}
