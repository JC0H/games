package objects;

import javax.swing.*;
import java.awt.*;

public class Player extends GlobalPosition{

    private String playerImage = "/resources/flower.png";

    public Player(int x, int y) {
        super(x, y);
    }

    public void update() {

    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(getPlayerImage(),x,y,null);
    }

    public Image getPlayerImage(){
        ImageIcon image = new ImageIcon(getClass().getResource(playerImage));
        return image.getImage();
    }
}
