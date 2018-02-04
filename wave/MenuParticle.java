package wave;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;
    private Color color;
    private Random rand = new Random();

    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (rand.nextInt(5 - -5) + -5);
        velY = (rand.nextInt(5 - -5) + -5);

        color = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    }

    public void tick() {
        x+= velX;
        y+= velY;

        if ( y <= 0 || y >= Game.HEIGHT - 45)  velY *= -1;
        if ( x <= 0 || x >= Game.WIDTH - 24)  velX *= -1;
        if (velX == 0) velX = 1;
        if (velY == 0) velY = 1;

        handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.02f,handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
