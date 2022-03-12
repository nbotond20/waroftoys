package model.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import model.Game;

public class TileManager {
    private Game gp;
    private HashMap<String, Tile> tileType;
    private HashMap<Integer, String> tileCodeTable;
    private ArrayList<ArrayList<String>> tiles;
    private int blockCount;
    public int[][] blocks;

    public TileManager(Game gp) {
        this.gp = gp;
        this.tileType = new HashMap<>();
        this.tileCodeTable = new HashMap<>();
        this.tiles = new ArrayList<ArrayList<String>>();
        getTileImage();
        loadMap();
    }

    // Loads tile images
    public void getTileImage() {
        try {
            Tile t1 = new Tile();
            t1.image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            tileType.put("grass", t1);
            tileCodeTable.put(0, "grass");

            Tile t2 = new Tile();
            t2.image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));
            tileType.put("road", t2);
            tileCodeTable.put(1, "road");

            Tile t3 = new Tile();
            t3.image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
            tileType.put("water", t3);
            tileCodeTable.put(2, "water");
        } catch (IOException e) {
        }
    }

    // Loads map layout files
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            String line;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                line = br.readLine();
                ArrayList<String> temp = new ArrayList<String>();
                while (col < gp.maxScreenCol) {
                    String type = tileCodeTable.get(Integer.parseInt(line.split(" ")[col]));
                    if (type != "road") {
                        blockCount++;
                    }
                    temp.add(type);
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
                tiles.add(temp);
            }
            br.close();

            if (blockCount > 0) {
                blocks = new int[blockCount][2];
                int k = 0;
                for (int i = 0; i < tiles.size(); i++) {
                    for (int j = 0; j < tiles.get(i).size(); j++) {
                        if (tiles.get(i).get(j) != "road") {
                            blocks[k] = new int[] { i, j };
                            k++;
                        }
                    }
                }
            }else{
                blocks = new int[1][2];
                blocks[0] = new int[] {-1, -1};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (row < gp.maxScreenRow) {
            g2.drawImage(tileType.get(tiles.get(row).get(col)).image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
