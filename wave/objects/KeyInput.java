package wave.objects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
        this.keyDown[3] = false;
    }


    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.Player){
                if (keyCode == KeyEvent.VK_W) {temp.setY(-5);keyDown[0] = true;}
                if (keyCode == KeyEvent.VK_S) {temp.setY(5);keyDown[1] = true;}
                if (keyCode == KeyEvent.VK_A) {temp.setX(-5);keyDown[2] = true;}
                if (keyCode == KeyEvent.VK_D) {temp.setX(5); keyDown[3] = true;}
            }
            if(keyCode == KeyEvent.VK_ESCAPE) System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp = handler.objects.get(i);

            if (temp.getId() == ID.Player){
                if (keyCode == KeyEvent.VK_W) {keyDown[0] = false;temp.setY(0);}
                if (keyCode == KeyEvent.VK_S) {keyDown[1] = false;temp.setY(0);}
                if (keyCode == KeyEvent.VK_A) {keyDown[2] = false;temp.setX(0);}
                if (keyCode == KeyEvent.VK_D) {keyDown[3] = false;temp.setX(0);}
            }
        }
    }
}
