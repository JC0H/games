package wave;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject{

    private Handler handler;
    private int timer = 85;
    private int timer2 = 50;
    private Random rand = new Random();

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velY = 2;
        velX = 0;
    }

    public void tick() {
        x+= velX;
        y+= velY;

        if (timer <= 0){
            velY = 0;
        }else{
            timer--;
        }

        if (timer <= 0 ) timer2--;
        if (timer2 <= 0){
            if (velX == 0) velX = 2;

            if (velX > 0)
            velX+= 0.01f;
            else if (velX < 0)
                velX-=0.01f;

            int spawn = rand.nextInt(10);
            if (spawn == 0) handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48, ID.EnemyBoss,handler));
        }

//        if ( y <= 0 || y >= Game.HEIGHT - 45)  velY *= -1;
        if ( x <= 0 || x >= Game.WIDTH - 96)  velX *= -1;
        handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.02f,handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,96,96);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}
