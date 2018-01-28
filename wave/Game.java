package wave;

import wave.objects.HP;
import wave.objects.Handler;
import wave.objects.ID;
import wave.objects.KeyInput;
import wave.objects.entity.Player;
import wave.objects.entity.StandartEnemy;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Window window;
    private BufferStrategy bs;
    private Graphics g;
    private Handler handler;
    private String title = "Wave 1.0";
    private Random rand = new Random();
    private HP health;

    public Game(){
        window = new Window(WIDTH,HEIGHT, this);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        health = new HP();




        handler.addObject(new Player(100,100, ID.Player, handler));
        handler.addObject(new StandartEnemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT),ID.SimpleEnemy, handler));
    }

    public synchronized void start(){
        this.requestFocus();
        running = true;
        thread = new Thread(()->{
            long jvmLastTime = System.nanoTime();
            long time = System.currentTimeMillis();
            double jvmPartTime = 1_000_000_000.0 / 60.0;
            double delta = 0;
            int updates = 0;
            int frames = 0;
            while (running){
                long jvmNowTime = System.nanoTime();
                delta += jvmNowTime - jvmLastTime;
                jvmLastTime = jvmNowTime;
                if (delta >=jvmPartTime){
                    tick();
                    updates++;
                    delta = 0;
                }
                render();
                frames++;

                if (System.currentTimeMillis() - time > 1000){
                    time+=1000;
                    window.setTitle(title ,updates, frames);
                    updates=0;
                    frames=0;
                }

            }
        });
        thread.start();
    }

    private void render() {
        bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);
        health.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        health.tick();
    }

    public static float clamp(float val, float min, float max ){
        if (val >= max) return val = max;
        else if (val <= min) return val = min;
        else return val;
    }

    public static void main(String[] args) {
        new Game().start();
    }

}
