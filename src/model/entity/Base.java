package model.entity;

import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;

public class Base extends Entity {
    private BufferedImage image;
    private Color color;

    public Base(Position pos, Game gp, MouseHandler mouseH){
        this.gp = gp;
        this.mouseH = mouseH;
        this.width = gp.tileSize * gp.scale;
        this.height = gp.tileSize * gp.scale;
        HEALTH_BAR_WIDTH = 100;

        getBaseImage("water");

        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.health = 100;
        this.MAX_HEALTH = 100;
        this.range = 200;

        this.color = Color.WHITE;
    }

    public void getBaseImage(String filename) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/bases/" + filename + ".png"));
        } catch (IOException e) {
            System.out.println("entity.Base.getBaseImage()");
        }
    }

    public void calcCorrectPosition(Position pos){
        int[] ind = getIndexFromPos(pos); 
        this.pos = new Position(getPosFromIndex(ind[0], ind[1]));
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        /* g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null); */
        g2.setColor(color);
        g2.fillRect((int) pos.x, (int) pos.y, width, height);
        g2.drawOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);

        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y, (int)(HEALTH_BAR_WIDTH*(health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y, HEALTH_BAR_WIDTH, 5);
    }
}
