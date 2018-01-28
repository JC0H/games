package wave.objects.entity;

import wave.Game;
import wave.objects.GameObject;
import wave.objects.HP;
import wave.objects.Handler;
import wave.objects.ID;

import java.awt.*;

public class Player extends GameObject {

    private Handler handler;

    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.WIDTH - 37);
        y = Game.clamp(y,0,Game.HEIGHT - 60);
        
        collisions();
    }

    private void collisions() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.SimpleEnemy){
                if (getBounds().intersects(temp.getBounds())){
                    HP.HEALTH -=2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
