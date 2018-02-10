import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {
    private String background = "/resources/backgrund.png";
    int a = 0;
    int b = 0;

    private Timer gameLoopTimer;
    private Player p;


    public Game(){
        System.out.println("it's alive!");

        gameLoopTimer = new Timer(10,this);
        gameLoopTimer.start();


        p = new Player(100,100);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(getBackgroudImage(),a,b,this);
        p.render(g2d);
    }

    public Image getBackgroudImage(){
        ImageIcon image = new ImageIcon(getClass().getResource(background));
        return image.getImage();
    }

    public void actionPerformed(ActionEvent e) {
        p.update();
        repaint();
    }
}
