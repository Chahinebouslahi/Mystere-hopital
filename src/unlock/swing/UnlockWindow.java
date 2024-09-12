package unlock.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import unlock.UnlockFont;

public class UnlockWindow extends JPanel {

    private ImageIcon icon;
    private JLabel window_title;
    private final JButton window_close;
    private final JButton window_minimize;
    private final JPanel head_panel;
    private double dragX, dragY;

    public UnlockWindow(int size, JFrame frame) {

        Border redBorder = new LineBorder(Color.RED, 3);
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 3, 3, 3);
        Border border = BorderFactory.createCompoundBorder(emptyBorder, redBorder);

        this.setLayout(new BorderLayout());
        this.setBorder(border);

        icon = new ImageIcon("src" + java.io.File.separator + "unlock" + java.io.File.separator + "images" + java.io.File.separator + "hospital.png");
        Image img_icon = (icon.getImage()).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img_icon);

        JLabel window_icon = new JLabel(icon);

        window_title = new JLabel("Unlock : Mystère à l'Hôpital");

        window_title.setFont(UnlockFont.setFont("Century Schoolbook", Font.BOLD, 12));
        window_title.setForeground(Color.WHITE);

        window_close = new JButton();
        window_close.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
        window_close.setBackground(Color.RED);
        window_close.setForeground(Color.WHITE);

        window_minimize = new JButton();
        window_minimize.setIcon(UIManager.getIcon("InternalFrame.iconifyIcon"));
        window_minimize.setBackground(Color.RED);
        window_minimize.setForeground(Color.WHITE);

        head_panel = new JPanel();

        head_panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {

                    dragX = e.getX();
                    dragY = e.getY();
                }
            }
        });

        head_panel.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (SwingUtilities.isLeftMouseButton(e)) {

                    int deltaX = (int) ((int) e.getX() - dragX);
                    int deltaY = (int) ((int) e.getY() - dragY);

                    frame.setLocation(frame.getX() + deltaX, frame.getY() + deltaY);
                }
            }
        });

        head_panel.setBackground(new Color(139, 69, 19));

        head_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        head_panel.add(window_icon);
        head_panel.add(Box.createRigidArea(new Dimension(5, 0)));
        head_panel.add(window_title);
        head_panel.add(Box.createRigidArea(new Dimension(size, 0)));
        head_panel.add(window_minimize);
        head_panel.add(window_close);

        this.add(head_panel, BorderLayout.NORTH);
        
        frame.setIconImage(icon.getImage());

        frame.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {

                    if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0) {
                        frame.setExtendedState(JFrame.NORMAL);
                    } else {
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            }
        });
    }

    public void setIcon(String icon) {

        this.icon = new ImageIcon(icon);
    }

    public void setWindowTitle(String window_title) {

        this.window_title = new JLabel(window_title);
    }

    public ImageIcon getIcon() {

        return this.icon;
    }

    public JLabel getWindowTitle() {

        return this.window_title;
    }

    public JButton getWindowClose() {

        return this.window_close;
    }

    public JButton getWindowMinimize() {

        return this.window_minimize;
    }
}
