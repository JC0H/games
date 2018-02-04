package wave;

import java.awt.*;
import java.util.ArrayList;

public class Handler {

    public ArrayList<GameObject> objects = new ArrayList<>();

    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g) {
        try {
            for (int i = 0; i < objects.size(); i++) {
                GameObject temp = objects.get(i);
                temp.render(g);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

    public void clearEnemys() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            if (temp.getId() == ID.Player){

                objects.clear();
                if (Game.gameState != Game.STATE.End)
                addObject(new Player((int)temp.getX(),(int)temp.getY(), ID.Player, this));
            }
        }
    }
}
