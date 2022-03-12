package model.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TileManager {
    private String PATH = "wood";

    public int maxRowNumber;
    public int maxColNumber;
    
    private Tile[][] grid;
    public int[][] blocks;
    private HashMap<String, BufferedImage> images;
    private HashMap<Integer, String> tileCodeTable;

    private int tileSize;

    public TileManager(int tileSize) {
        this.images = new HashMap<String, BufferedImage>();
        this.tileCodeTable = new HashMap<Integer, String>();
        this.tileSize = tileSize;

        loadTileImages();
        loadMap("map1.txt");
    }

    private void loadTileImages() {
        try {
            tileCodeTable.put(0, "wood");
            images.put("wood", ImageIO.read(getClass().getResourceAsStream("/tiles/wood-64x64.png")));

            tileCodeTable.put(1, "water");
            images.put("water", ImageIO.read(getClass().getResourceAsStream("/tiles/water-64x64.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filename) {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/" + filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String[] dimensions = br.readLine().split(" ");
            maxColNumber = Integer.parseInt(dimensions[0]);
            maxRowNumber = Integer.parseInt(dimensions[1]);
            grid = new Tile[maxRowNumber][maxColNumber];   

            int blockCount = 0;
            
            for(int i = 0; i < maxRowNumber; i++) {
                String[] tileNumbers = br.readLine().split(" ");
                for (int j = 0; j < maxColNumber; j++) {
                    Tile t = new Tile();

                    int tileNum = Integer.parseInt(tileNumbers[j]);
                    t.image = images.get(tileCodeTable.get(tileNum));

                    if(tileCodeTable.get(tileNum) != PATH){
                        t.collision = true;
                        blockCount++;
                    }
                    grid[i][j] = t;
                }
            }

            if (blockCount > 0) {
                blocks = new int[blockCount][2];
                int k = 0;
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j].collision) {
                            blocks[k] = new int[] { i, j };
                            k++;
                        }
                    }
                }
            }else{
                blocks = new int[1][2];
                blocks[0] = new int[] {-1, -1};
            }
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g2.drawImage(grid[i][j].image, tileSize * j, tileSize * i, tileSize, tileSize, null);
            }
        }
    }
}
