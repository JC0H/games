package Map;

import java.util.Random;

public class Render {

    private int width,height;
    private int[] pixels;
    private static final int MAP_SIZE = 8;
    private static final int MAP_SIZE_MASK = MAP_SIZE - 1;
    private int[] tiles = new int[MAP_SIZE*MAP_SIZE];
    private static Random rand = new Random();

    public Render(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        fill();
    }

    public void fill(){
        for (int i = 0; i < MAP_SIZE* MAP_SIZE; i++) {
            tiles[i] = rand.nextInt(0xffffff);
        }
    }

    public void render(int xOffset, int yOffset){
        for (int x = 0; x < width; x++){
            int xx = x + xOffset;
            for (int y = 0; y < height; y ++){
                int yy = y + yOffset;
                int tilesIndex = ((yy >> 4) & MAP_SIZE_MASK) + (((xx >> 4) & MAP_SIZE_MASK) * MAP_SIZE);

                pixels[x + (y * width)] = tiles[tilesIndex];
            }
        }
    }
}
