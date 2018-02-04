package wave;

import java.awt.*;

public class Player extends GameObject{

    private Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;


    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.WIDTH - 48);
        y = Game.clamp(y,0,Game.HEIGHT - 70);

        handler.addObject(new Trail(x,y,ID.Trail,Color.pink,32,32,0.05f,handler));
        collisions();
    }

    private void collisions() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);
            if (temp.getId() == ID.BasicEnemy || temp.getId() == ID.SmartEnemy|| temp.getId() == ID.FastEnemy || temp.getId() == ID.EnemyBoss){
                if (getBounds().intersects(temp.getBounds())){
                    HP.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
