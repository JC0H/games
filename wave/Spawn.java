package wave;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HP hp;
    private Random rand = new Random();
    private int scoreIndex = 0;

    public Spawn(HP hp, Handler handler){
        this.hp = hp;
        this.handler = handler;
    }


    public void tick(){
        scoreIndex++;
        if (scoreIndex >= 250){
            scoreIndex = 0;
            hp.setLevel(hp.getLevel() + 1);

            if (hp.getLevel() == 2){
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.BasicEnemy,handler));
            }else if (hp.getLevel() == 3){
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.FastEnemy,handler));
            }else if (hp.getLevel() == 4){
                handler.addObject(new SmartEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.SmartEnemy,handler));
            }else if (hp.getLevel() == 5){
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.BasicEnemy,handler));
            }else if (hp.getLevel() == 6){
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.FastEnemy,handler));
            }else if (hp.getLevel() == 7){
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.BasicEnemy,handler));
            }else if (hp.getLevel() == 8){
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50),rand.nextInt(Game.WIDTH - 50),ID.BasicEnemy,handler));
            }else if (hp.getLevel() == 10){
                handler.clearEnemys();
                handler.addObject(new EnemyBoss((Game.WIDTH / 2) -48, -120,ID.EnemyBoss,handler));
            }
        }
    }
}
