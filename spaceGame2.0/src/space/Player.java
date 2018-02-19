package space;

import java.awt.*;

public class Player {

    private double x, y;
    private double velX, velY;
    private Textures tex;

    public Player(double x, double y, Textures tex){
        this.x = x;
        this.y = y;
        this.tex = tex;
    }

    public void tick(){
        x+= velX;
        y+= velY;

        if (x <= 0) x = 0;
        if (x >= 593)x = 593;
        if (y <= 0)y = 0;
        if (y >= 406) y = 406;

    }

    public void render(Graphics g){
        g.drawImage(tex.player,(int)x,(int)y,null);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}
