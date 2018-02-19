package space;

import java.awt.*;

public class Bullet {

    private double x, y;

    private Textures tex;

    public Bullet(double x, double y,Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void tick(){
        y- =10;
    }

    public void render(Graphics g){
        g.drawImage(tex.missile,(int)x,(int)y,null);
    }

    public double getY() {
        return y;
    }
}
