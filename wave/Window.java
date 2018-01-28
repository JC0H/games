package wave;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    private JFrame frame;

    public Window(int width, int height, Game game){

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setTitle(String title, int updates, int frames ) {
        frame.setTitle(title + " | updates: " + updates + " , frames: " + frames);
    }
}
