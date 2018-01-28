package wave.objects;

import wave.Game;

import java.awt.*;

public class HP {

    public static float HEALTH = 100;

    public void tick(){
        HEALTH--;
        HEALTH = Game.clamp((int)HEALTH,0,100);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(Color.GREEN);
        g.fillRect(15,15,(int)HEALTH*2,32);
        g.setColor(Color.gray);
        g.drawRect(15,15,200,32);
    }
}
