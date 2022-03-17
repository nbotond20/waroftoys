package model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Game;
import model.utility.Position;

public class Base extends Entity {
    private BufferedImage image;
    private final Color color;

    public Base(final Position pos, final Game gp){
        this.game = gp;
        this.mouseH = gp.mouseH;
        this.width = gp.tileSize * gp.scale;
        this.height = gp.tileSize * gp.scale;
        HEALTH_BAR_WIDTH = 60;

        getBaseImage("water");

        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.health = 100;
        this.MAX_HEALTH = 100;
        this.range = 200;

        this.color = Color.WHITE;
    }

    public void getBaseImage(final String filename) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/bases/" + filename + ".png"));
        } catch (final IOException e) {
            System.out.println("entity.Base.getBaseImage()");
        }
    }

    public void calcCorrectPosition(final Position pos){
        final int[] ind = getIndexFromPos(pos); 
        this.pos = new Position(getPosFromIndex(ind[0], ind[1]));
    }

    public void draw(final Graphics2D g2) {


        g2.setColor(Color.GREEN);
        /* g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null); */
        g2.setColor(color);
        g2.fillRect((int) pos.x, (int) pos.y, width, height);
        if(pos.x == game.hoverPosition.x && pos.y == game.hoverPosition.y){
            g2.setColor(new Color(100, 100, 100, 100));
            g2.fillOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);
            g2.drawOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);
        }

        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, (int)(HEALTH_BAR_WIDTH*(health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, HEALTH_BAR_WIDTH, 5);
    }
}
