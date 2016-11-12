package paint.view;

import paint.model.ColorPoint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.SwingUtilities.invokeLater;

public final class PainterSingleton {

    private static PainterSingleton instance = null;

    private JFrame frame;

    private int oldX;
    private int oldY;

    private List<ArrayList<ColorPoint>> colorPointList;

    private Color color;
    private int thickness;

    private JPanel buttonsPanel = new JPanel();
    private JButton buttonChooseColor;
    private JLabel labelChooseThickness;
    private JButton buttonDisplayCurrentColor;
    private JRadioButton radioButtonThickness5;
    private JRadioButton radioButtonThickness10;
    private JRadioButton radioButtonThickness15;
    private JRadioButton radioButtonThickness20;
    private ButtonGroup thicknessesButtonGroup;

    private Toolkit toolkit;
    private Image image;
    private Cursor imageCursor;

    private JButton buttonEraser;


    public static synchronized PainterSingleton getInstance() {
        if (instance == null) {
            instance = new PainterSingleton();
        }
        return instance;
    }

    private PainterSingleton() {
        initialize();
    }

    private void initialize() {
        colorPointList = new ArrayList<ArrayList<ColorPoint>>();

        toolkit = Toolkit.getDefaultToolkit();

        color = new Color(0, 0, 0);
        thickness = 5;

        frame = new JFrame("Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(750, 750));
        frame.setLocation(95, 95);

        buttonsPanel = new JPanel();
        buttonChooseColor = new JButton("Choose brush color");

        image = toolkit.getImage("resources/pencil.png");
        buttonChooseColor.setIcon(new ImageIcon(image));


        buttonChooseColor.setToolTipText("Click here to choose brash color");
        buttonChooseColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                //frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);


            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

        buttonChooseColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Color chooser run");
                invokeLater(new Runnable() {
                    public void run() {
                        ColorChooser.createAndShowGUI();
                    }
                });
            }
        });


        buttonDisplayCurrentColor = new JButton();
        buttonDisplayCurrentColor.setEnabled(false);
        buttonDisplayCurrentColor.setBackground(color);
        buttonDisplayCurrentColor.setPreferredSize(new Dimension(26, 26));

        labelChooseThickness = new JLabel("                 Choose brush thickness:");



        radioButtonThickness5 = new JRadioButton("5 ");
        radioButtonThickness5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("выбрана толщина 5");
                thickness = 5;
            }
        });

        radioButtonThickness10 = new JRadioButton("10 ");
        radioButtonThickness10.setSelected(true);
        radioButtonThickness10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("выбрана толщина 10");
                thickness = 10;
            }
        });

        radioButtonThickness15 = new JRadioButton("15 ");
        radioButtonThickness15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("выбрана толщина 15");
                thickness = 15;
            }
        });

        radioButtonThickness20 = new JRadioButton("20 ");
        radioButtonThickness20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("выбрана толщина 20");
                thickness = 20;
            }
        });

        thicknessesButtonGroup = new ButtonGroup();
        thicknessesButtonGroup.add(radioButtonThickness5);
        thicknessesButtonGroup.add(radioButtonThickness10);
        thicknessesButtonGroup.add(radioButtonThickness15);
        thicknessesButtonGroup.add(radioButtonThickness20);

        image = toolkit.getImage("resources/eraser.png");
        buttonEraser = new JButton("Eraser");
        buttonEraser.setToolTipText("Choose eraser to clear fragment of the image");
        buttonEraser.setIcon(new ImageIcon(image));



        buttonsPanel.add(buttonChooseColor);
        buttonsPanel.add(buttonDisplayCurrentColor);
        buttonsPanel.add(labelChooseThickness);
        buttonsPanel.add(radioButtonThickness5);
        buttonsPanel.add(radioButtonThickness10);
        buttonsPanel.add(radioButtonThickness15);
        buttonsPanel.add(radioButtonThickness20);
        buttonsPanel.add(buttonEraser);

        frame.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.white);
        frame.pack();


        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("Нажатие состоялось!");

                Graphics g = frame.getGraphics();
                g.setColor(color);

                oldX = e.getX();
                oldY = e.getY();

                g.fillOval(oldX, oldY, thickness, thickness);

                colorPointList.add(new ArrayList<ColorPoint>());
                colorPointList.get(colorPointList.size() - 1).add(new ColorPoint(e.getX(), e.getY(), thickness, color));
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                Graphics g = frame.getGraphics();
                g.setColor(color);
                oldX = e.getX();
                oldY = e.getY();

                g.fillOval(oldX, oldY, thickness, thickness);

                colorPointList.get(colorPointList.size() - 1).add(new ColorPoint(e.getX(), e.getY(), thickness, color));
            }
        });

        frame.setVisible(true);


        image = toolkit.getImage("resources/pencilBig.png");
        imageCursor = toolkit.createCustomCursor(image, new Point(7, 7), "imageCursor");

        frame.setCursor(imageCursor);
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public void changeButtonColor(Color newColor) {
        buttonDisplayCurrentColor.setBackground(newColor);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < colorPointList.size(); i++) {
            //System.out.println(" " + i);
            g.setColor(colorPointList.get(i).get(0).getColor());
            for (int j = 0; j < colorPointList.get(i).size(); j++) {
                g.fillOval((int) (colorPointList.get(i).get(j).getX()),(int) (colorPointList.get(i).get(j).getY()), (int) (colorPointList.get(i).get(j).getDepth()), (int) (colorPointList.get(i).get(j).getDepth()));
            }
        }

    }

}



