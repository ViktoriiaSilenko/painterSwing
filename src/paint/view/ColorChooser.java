package paint.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


/* ColorChooserDemo.java requires no other files. */
public class ColorChooser extends JPanel
        implements ChangeListener {

    protected JColorChooser tcc;
    protected JLabel banner;

    public ColorChooser() {
        super(new BorderLayout());

        //Set up the banner at the top of the window
        banner = new JLabel("Welcome to the painter program", JLabel.CENTER);
        banner.setForeground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("About program"));

        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose brash Color"));

        //add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        PainterSingleton painterSingleton = PainterSingleton.getInstance();
        painterSingleton.setColor(newColor);
        painterSingleton.changeButtonColor(newColor);
        //banner.setForeground(newColor);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Choose color");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooser();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

}