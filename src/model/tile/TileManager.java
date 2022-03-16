package model.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TileManager {
    private final String DEFAULT = "wood";

    public int maxRowNumber;
    public int maxColNumber;

    private Tile[][] grid;
    private Obstacle[][] obstacleGrid;
    public int[][] blocks;
    private final HashMap<String, BufferedImage> images;
    private final HashMap<Integer, String> tileCodeTable;
    private final HashMap<String, Integer[]> obstacleSizeTable;

    private final int tileSize;

    public TileManager(final int tileSize) {
        this.images = new HashMap<String, BufferedImage>();
        this.tileCodeTable = new HashMap<Integer, String>();
        this.obstacleSizeTable = new HashMap<String, Integer[]>();
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

            tileCodeTable.put(2, "car");
            obstacleSizeTable.put("car", new Integer[] { 4, 7 });
            images.put("car", ImageIO.read(getClass().getResourceAsStream("/tiles/car-165x165.png")));

            tileCodeTable.put(3, "bottle");
            obstacleSizeTable.put("bottle", new Integer[] { 2, 4 });
            images.put("bottle", ImageIO.read(getClass().getResourceAsStream("/tiles/bottle-165x165.png")));

            tileCodeTable.put(4, "pencil");
            obstacleSizeTable.put("pencil", new Integer[] { 1, 5 });
            images.put("pencil", ImageIO.read(getClass().getResourceAsStream("/tiles/pencil-165x165.png")));

            tileCodeTable.put(5, "domino");
            obstacleSizeTable.put("domino", new Integer[] { 4, 9 });
            images.put("domino", ImageIO.read(getClass().getResourceAsStream("/tiles/domino-165x165.png")));

            tileCodeTable.put(6, "dices");
            obstacleSizeTable.put("dices", new Integer[] { 4, 4 });
            images.put("dices", ImageIO.read(getClass().getResourceAsStream("/tiles/dices-165x165.png")));

            tileCodeTable.put(7, "bear");
            obstacleSizeTable.put("bear", new Integer[] { 10, 8 });
            images.put("bear", ImageIO.read(getClass().getResourceAsStream("/tiles/bear-165x165.png")));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(final String filename) {
        try {
            final InputStream is = getClass().getResourceAsStream("/maps/" + filename);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            final String[] dimensions = br.readLine().split(" ");
            maxColNumber = Integer.parseInt(dimensions[0]);
            maxRowNumber = Integer.parseInt(dimensions[1]);
            grid = new Tile[maxRowNumber][maxColNumber];
            obstacleGrid = new Obstacle[maxRowNumber][maxColNumber];

            int blockCount = 0;

            for (int i = 0; i < maxRowNumber; i++) {
                final String[] tileNumbers = br.readLine().split(" ");
                for (int j = 0; j < maxColNumber; j++) {
                    final Tile t = new Tile();

                    final int tileNum = Integer.parseInt(tileNumbers[j]);
                    if (tileNum <= 1)
                        t.image = images.get(tileCodeTable.get(tileNum));
                    else {
                        final Obstacle obstacle = new Obstacle();
                        obstacle.image = images.get(tileCodeTable.get(tileNum));
                        obstacle.width = obstacleSizeTable.get(tileCodeTable.get(tileNum))[0];
                        obstacle.height = obstacleSizeTable.get(tileCodeTable.get(tileNum))[1];
                        obstacleGrid[i][j] = obstacle;
                        t.image = images.get(DEFAULT);
                    }

                    if (tileCodeTable.get(tileNum) != DEFAULT) {
                        t.collision = true;
                        blockCount++;
                    }
                    
                    if (obstacleGrid[i][j] != null) {
                        for(int e=0; e<obstacleGrid[i][j].width; e++){
                            for(int f=0; f<obstacleGrid[i][j].height; f++){
                                if(!(e==0 && f==0) && (i + f < grid.length - 1 && j + e < grid[0].length - 1)){
                                    blockCount++;
                                }
                            }
                        }
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
                        if (obstacleGrid[i][j] != null) {
                            for (int e = 0; e < obstacleGrid[i][j].width; e++) {
                                for (int f = 0; f < obstacleGrid[i][j].height; f++) {
                                    if (!(e == 0 && f == 0) && (i + f < grid.length - 1 && j + e < grid[0].length - 1) && grid[i][j].collision) {
                                        blocks[k] = new int[] { i + f, j + e };
                                        k++;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                blocks = new int[1][2];
                blocks[0] = new int[] { -1, -1 };
            }

            for(final int[] i : blocks) {
                grid[i[0]][i[1]].collision = true;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(final Graphics2D g2) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g2.drawImage(grid[i][j].image, tileSize * j, tileSize * i, tileSize, tileSize, null);
            }
        }

        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] != null)
                    g2.drawImage(obstacleGrid[i][j].image, tileSize * j, tileSize * i,
                            tileSize * obstacleGrid[i][j].width, tileSize * obstacleGrid[i][j].height, null);
            }
        }
    }
}