package objects;

import java.awt.*;

public abstract class GlobalPosition {

    protected int x, y;

    public GlobalPosition(int x , int y){
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics2D g2d);
}
