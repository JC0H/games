package wave.objects.entity;

import wave.objects.GameObject;
import wave.objects.ID;

import java.awt.*;

public class SmartEnemy extends GameObject {

    public SmartEnemy(float x, float y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
