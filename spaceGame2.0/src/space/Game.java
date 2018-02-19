package space;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas{

    private Window window;
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private String title = "SPACE INVASION";

    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean is_shooting = false;
    private Player p;
    private Handler handler;
    private Textures tex;

    public Game(){
        requestFocus();
        window = new Window(WIDTH, HEIGHT, this);
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("/space/resources/images/sprite_sheet.png");
            background = loader.loadImage("/space/resources/images/background.png");
        }catch(IOException e){
            System.out.println("something go wrong");
        }
        addKeyListener(new KeyInput(this));

        tex = new Textures(this);
        p = new Player(100,100, tex);
        handler = new Handler(this,tex);
    }

    private void start() {
        running = true;
        thread = new Thread(()->{
            double delta = 0;
            double jvmPartTime = 1_000_000_000.0 / 60.0;
            double time = System.currentTimeMillis();
            double jvmLastTime = System.nanoTime();
            double updates = 0;
            double frames = 0;
            while (running){
                double jvmCurrentTime = System.nanoTime();
                delta+=jvmCurrentTime - jvmLastTime;
                jvmLastTime = jvmCurrentTime;
                if(delta >= jvmPartTime){
                    delta = 0;
                    updates++;
                    tick();
                }
                render();
                frames++;

                if (System.currentTimeMillis() - time >= 1000){
                    time+= 1000;
                    window.setTitle(title, frames,updates);
                    updates = 0;
                    frames = 0;
                }
            }
        });
        thread.start();
    }

    private void tick() {
        p.tick();
        handler.tick();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)p.setVelY(-5);
        if (key == KeyEvent.VK_S)p.setVelY(5);
        if (key == KeyEvent.VK_A)p.setVelX(-5);
        if (key == KeyEvent.VK_D)p.setVelX(5);
        if (key == KeyEvent.VK_ESCAPE)System.exit(1);
        if (key == KeyEvent.VK_SPACE && !is_shooting){
            is_shooting = true;
            handler.addBullet(new Bullet(p.getX(),p.getY(),tex));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)p.setVelY(0);
        if (key == KeyEvent.VK_S)p.setVelY(0);
        if (key == KeyEvent.VK_A)p.setVelX(0);
        if (key == KeyEvent.VK_D)p.setVelX(0);
        if (key == KeyEvent.VK_SPACE) is_shooting = false;
    }

    private void render() {
        if (bs == null)
            createBufferStrategy(3);
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();
////////////////////////////////////////////

        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        g.setColor(Color.red);
        g.fillRect(0,0,800,800);

        g.drawImage(background,0,0,null);

        p.render(g);
        handler.render(g);

////////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public static void main(String[] args) {
        new Game().start();
    }

}
