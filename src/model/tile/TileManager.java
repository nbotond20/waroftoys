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

    public Tile[][] grid;
    public Tile[][] obstacles;
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
            obstacleSizeTable.put("wood", new Integer[] { 1, 1 });
            images.put("wood", ImageIO.read(getClass().getResourceAsStream("/tiles/wood-64x64.png")));

            tileCodeTable.put(1, "water");
            obstacleSizeTable.put("water", new Integer[] { 1, 1 });
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
            int blockCount = 0;

            final InputStream is = getClass().getResourceAsStream("/maps/" + filename);
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            final String[] dimensions = br.readLine().split(" ");
            maxColNumber = Integer.parseInt(dimensions[0]);
            maxRowNumber = Integer.parseInt(dimensions[1]);
            grid = new Tile[maxRowNumber][maxColNumber];
            obstacles = new Tile[maxRowNumber][maxColNumber];

            for (int i = 0; i < maxRowNumber; i++) {
                String[] numbers = br.readLine().split(" ");
                for (int j = 0; j < maxColNumber; j++) {
                    int tileNum = Integer.parseInt(numbers[j]);
                    String tileType = tileCodeTable.get(tileNum);

                    Tile tile = new Tile();
                    tile.image = images.get(tileType);
                    tile.type = tileType;
                    tile.dimension = obstacleSizeTable.get(tileCodeTable.get(tileNum));
                    if (tileNum > 1) {
                        tile.bitMap = loadBitMap(tileType, tile.dimension[0], tile.dimension[1]);
                        tile.secondaryImage = images.get("wood");
                        obstacles[i][j] = tile;
                    }else{
                        obstacles[i][j] = null;
                    }
                    grid[i][j] = tile;
                }
            }

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].type == "water") {
                        grid[i][j].collision = true;
                        blockCount++;
                    } else if (grid[i][j].type != DEFAULT) {
                        for (int e = 0; e < grid[i][j].dimension[0]; e++) {
                            for (int f = 0; f < grid[i][j].dimension[1]; f++) {
                                if ((i + f < grid.length - 1 && j + e < grid[i].length - 1) && grid[i][j].bitMap[f][e] == 0) {
                                    grid[i + f][j + e].collision = true;
                                    blockCount++;
                                }
                            }
                        }
                    }
                }
            }

            blocks = new int[blockCount][2];
            int k = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j].collision) {
                        blocks[k] = new int[]{i, j};
                        k++;
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(final Graphics2D g2) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].type == DEFAULT || grid[i][j].type == "water"){
                    g2.drawImage(grid[i][j].image, tileSize * j, tileSize * i, tileSize, tileSize, null);
                }
                else{
                    g2.drawImage(grid[i][j].secondaryImage, tileSize * j, tileSize * i, tileSize, tileSize, null);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].type != DEFAULT && grid[i][j].type != "water"){
                    g2.drawImage(grid[i][j].image, tileSize * j, tileSize * i, tileSize*grid[i][j].dimension[0], tileSize*grid[i][j].dimension[1], null);
                }
            }
        }
    }

    public void addToBlocks(int[] indexes) {
        int i;
        int newarray[][] = new int[blocks.length + 1][2];

        for (i = 0; i < blocks.length - 1; i++)
            newarray[i] = blocks[i];

        newarray[blocks.length - 1] = indexes;
        blocks = newarray;
    }

    public Integer[][] loadBitMap(String filename, int width, int height) {
        Integer[][] result = new Integer[height][width];
        try {
            final InputStream is = getClass().getResourceAsStream("/bitmaps/" + filename + ".txt");
            final BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < height; i++) {
                String[] numbers = br.readLine().split(" ");
                for (int j = 0; j < width; j++) {
                    result[i][j] = Integer.parseInt(numbers[j]);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
