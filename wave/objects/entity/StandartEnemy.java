package wave.objects.entity;

import wave.Game;
import wave.objects.GameObject;
import wave.objects.Handler;
import wave.objects.ID;

import java.awt.*;

public class StandartEnemy extends GameObject {

    private Handler handler;

    public StandartEnemy(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y +=velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x,y,ID.SimpleEnemy,Color.red,16,16,0.02f,handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
