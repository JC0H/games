package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window extends Canvas{

    private static final int WIDTH = 300;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCOPE = 3;
    private JFrame frame = new JFrame();
    private String title = "Map 1.0";
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private Render renderer;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int x,y;

    public Window(){
        setPreferredSize(new Dimension(WIDTH * SCOPE,HEIGHT * SCOPE));
        renderer = new Render(WIDTH,HEIGHT,pixels);
        frame.addKeyListener(new KeyInput());
    }

    public void start(){
        this.requestFocus();
        running = true;
        init();
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
                    update();
                    updates++;
                    delta = 0;
                }
                render();
                frames++;

                if (System.currentTimeMillis() - time > 1000){
                    time+=1000;
                    frame.setTitle(title + " | updates: " + updates + " , frames: " + frames);
                    updates=0;
                    frames=0;
                }

            }
        });
        thread.start();
    }

    private void update() {
        if (KeyInput.isPressed(KeyEvent.VK_LEFT))x--;
        if (KeyInput.isPressed(KeyEvent.VK_RIGHT))x++;
        if (KeyInput.isPressed(KeyEvent.VK_UP))y--;
        if (KeyInput.isPressed(KeyEvent.VK_DOWN))y++;
    }

    private void render() {
        if (bs == null)
            createBufferStrategy(3);
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();

        renderer.render(x,y);
        //g.setColor(Color.BLACK);
        //g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(image,0,0,getWidth(),getHeight(),null);

        g.dispose();
        bs.show();
    }

    private void init() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Window().start();
    }
}
