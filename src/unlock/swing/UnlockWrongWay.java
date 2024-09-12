package unlock.swing;

import unlock.UnlockFont;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public final class UnlockWrongWay extends JFrame {

    public UnlockWrongWay(String message) {

        this.initComponents(message);
        this.setTitle("Unlock : Mystère à l'Hôpital");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents(String message) {

        UnlockWindow window = new UnlockWindow(250, this);

        window.getWindowClose().addActionListener((ActionEvent e) -> {
            this.dispose();
        });

        window.getWindowMinimize().addActionListener((ActionEvent e) -> {
            this.setState(JFrame.ICONIFIED);
        });

        JPanel center_panel = new JPanel();

        JLabel title = new JLabel(message);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(UnlockFont.setFont("Century Schoolbook", Font.BOLD, 12));

        JButton close_button = new JButton("Fermer");
        close_button.setFont(UnlockFont.getButtonFont());
        close_button.setBackground(new Color(139, 69, 19));
        close_button.setForeground(Color.WHITE);

        close_button.addActionListener(e -> {
            this.dispose();
        });

        center_panel.setLayout(new BorderLayout());
        center_panel.add(title, BorderLayout.CENTER);
        center_panel.add(close_button, BorderLayout.SOUTH);

        window.add(center_panel);
        
        window.setBackground(Color.RED);

        this.getContentPane().add(window);
    }
}
