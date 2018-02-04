package wave;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas {

    public static final int WIDTH = 640,HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private Handler handler;
    private Window window;
    private String title = "FPS : ";
    private HP hp;
    private Spawn spawn;
    private static Random rand = new Random();
    private Menu menu;

    public enum STATE{
        Menu,
        Help,
        End,
        Game;
    };

    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        hp = new HP();
        menu = new Menu(this,handler ,hp);
        addKeyListener(new KeyInput(handler));
        addMouseListener(menu);

        window = new Window(WIDTH, HEIGHT, this);
        spawn = new Spawn(hp,handler);

        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.WIDTH - 50), ID.BasicEnemy, handler));
        }else{
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }
    }

    private void start(){
        this.requestFocus();
        running = true;
        thread = new Thread(() -> {
            double jvmPartTime = 1_000_000_000.0 / 60.0;
            double delta = 0;
            long jvmLastTime = System.nanoTime();
            long updates = 0;
            long frames = 0;
            long time = System.currentTimeMillis();
            while(running){
                long jvmCurrentTime = System.nanoTime();
                delta +=  jvmCurrentTime - jvmLastTime;
                jvmLastTime = jvmCurrentTime;
                if (delta > jvmPartTime){
                    tick();
                    updates++;
                    delta = 0;
                }
                render();
                frames++;
                
                if (System.currentTimeMillis() - time > 1000){
                    time+= 1000;
                    window.setTitle(title, updates, frames);
                    
                    updates = 0;
                    frames = 0;
                }
            }
        });
        thread.start();
    }

    private void tick(){
        handler.tick();

        if (gameState == STATE.Game) {
            hp.tick();
            spawn.tick();

            if (HP.HEALTH <= 0){
                HP.HEALTH = 100;
                handler.clearEnemys();
//                hp.setLevel(1);
//                hp.setScore(0);
                gameState = STATE.End;

                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ID.MenuParticle, handler));
                }
            }

        }else if (gameState == STATE.Menu || gameState == STATE.End ){
            menu.tick();
        }
    }

    private void render() {
        if (bs == null)
            createBufferStrategy(3);
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        handler.render(g);
        if (gameState == STATE.Game) {
            hp.render(g);
        }else if (gameState == STATE.Menu ||gameState == STATE.Help ){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float alpha, float min, float max){
        if (alpha >= max) return alpha = max;
        else if (alpha <= min) return alpha = min;
        else return alpha;
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
