package space;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    private Game game;
    private Textures tex;

    public LinkedList<Bullet> b = new LinkedList<>();
    public LinkedList<Enemy> e = new LinkedList<>();

    public Handler(Game game,Textures tex){
        this.game = game;
        this.tex = tex;

        for (int i = 0; i < (Game.WIDTH * Game.SCALE); i+=64) {
            addEnemy(new Enemy(i,0,tex));
        }
    }

    public void tick(){
        for (int i = 0; i < b.size(); i++) {
            Bullet temp = b.get(i);

            if (temp.getY() < 0) removeBullet(temp);

            temp.tick();
        }

        for (int i = 0; i < e.size(); i++) {
            Enemy temp = e.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < b.size(); i++) {
            Bullet temp = b.get(i);
            temp.render(g);
        }

        for (int i = 0; i < e.size(); i++) {
            Enemy temp = e.get(i);
            temp.render(g);
        }
    }

    public void addBullet(Bullet bull){
        b.add(bull);
    }

    public void removeBullet(Bullet bull){
        b.remove(bull);
    }

    public void addEnemy(Enemy bull){
        e.add(bull);
    }

    public void removeEnemy(Enemy bull){
        e.remove(bull);
    }

}
