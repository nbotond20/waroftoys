package model.entity.tower;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Game;
import model.entity.Entity;
import model.utility.MouseHandler;

public abstract class Tower extends Entity {
    private BufferedImage image;
    public Color color;

    public Tower(final Game gp, final MouseHandler mouseH){
        this.gp = gp;
        this.mouseH = mouseH;
        this.width = gp.tileSize * gp.scale;
        this.height = gp.tileSize * gp.scale;
        HEALTH_BAR_WIDTH = 50;
    }

    public void getTowerImage(final String filename) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/towers/" + filename + ".png"));
        } catch (final IOException e) {
            System.out.println("entity.Tower.getTowerImage()");
        }
    }

    public void draw(final Graphics2D g2) {
        g2.setColor(new Color(100, 100, 100, 100));
        g2.fillOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);

        /* g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null); */
        g2.setColor(color);
        g2.fillOval((int) pos.x, (int) pos.y, width, height);
        g2.drawOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);
        
        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, (int)(HEALTH_BAR_WIDTH*(health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, HEALTH_BAR_WIDTH, 5);
    }
}
