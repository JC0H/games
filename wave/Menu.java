package wave;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu  extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Random rand = new Random();
    private HP hp;

    public Menu(Game game, Handler handler,HP hp){
        this.game = game;
        this.hp = hp;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu){
            //play button
            if (mouseOver(mx,my,210,150,200,64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.WIDTH - 50), ID.BasicEnemy, handler));
            }

            //help button
            if (mouseOver(mx,my,210,250,200,64)){
                game.gameState = Game.STATE.Help;
            }


            //quit button
            if (mouseOver(mx,my,210,350,200,64)){
                System.exit(1);
            }
        }

        //back button for help
        if (game.gameState == Game.STATE.Help){
            if (mouseOver(mx,my,210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x+ width){
            if (my > y && my < y + height){
                return  true;
            }else return false;
        }return false;
    }

    public void render(Graphics g){
        if (game.gameState == Game.STATE.Menu) {
            Font font = new Font("arial", 1, 50);
            Font font1 = new Font("arial", 1, 30);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Wave", 240, 70);

            g.setFont(font1);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        }else if (game.gameState == Game.STATE.Help){
            Font font = new Font("arial", 1, 50);
            Font font1 = new Font("arial", 1, 30);
            Font font2 = new Font("arial", 1, 20);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Help",240,70);

            g.setFont(font2);
            g.drawString("Use WSAD to move you player and doodle enemies",50,200);

            g.setFont(font1);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }else if (game.gameState == Game.STATE.End ){
            Font font = new Font("arial", 1, 50);
            Font font1 = new Font("arial", 1, 30);
            Font font2 = new Font("arial", 1, 20);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Game Over",180,70);

            g.setFont(font2);
            g.drawString("You lose with a score " + hp.getScore(),175,200);

            g.setFont(font1);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }
    }

    public void tick(){

    }
}
