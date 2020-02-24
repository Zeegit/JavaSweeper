import javax.swing.*;
import sweeper.*;
import sweeper.Box;

import java.awt.*;

public class JavaSweeper extends JFrame {
    private Game game;

    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        intiFrame();

    }

    private void initPanel() {

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                for(Coord coord : Ranges.getAllCoords()) {
                     g.drawImage((Image) game.getBox(coord).getImage(), coord.getX() * IMAGE_SIZE, coord.getY() * IMAGE_SIZE, this);
                }

            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().getX() * IMAGE_SIZE, Ranges.getSize().getY() * IMAGE_SIZE));
        add(panel);
    }

    private void intiFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
    }

    private void setImages() {
        for(Box box : Box.values()) {
            box.setImage(getImage(box.name().toLowerCase()));
        }
    }

    private Image getImage(String name) {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

}
