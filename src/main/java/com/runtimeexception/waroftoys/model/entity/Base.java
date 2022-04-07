package com.runtimeexception.waroftoys.model.entity;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Base extends Entity {
    private static final boolean DRAW_IMAGE = false;
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

        if(DRAW_IMAGE){
            g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null);
        }else{
            g2.setColor(color);
            g2.fillRect((int) pos.x, (int) pos.y, width, height);
        }
        if(pos.x == game.hoverPosition.x && pos.y == game.hoverPosition.y){
            g2.setColor(new Color(100, 100, 100, 100));
            g2.fillOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);
            g2.setColor(color);
            g2.drawOval((int) (pos.x + width/2 - range/2), (int) (pos.y + width/2 - range/2), (int)range, (int)range);
        }

        g2.setColor(Color.RED);
        g2.fillRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, (int)(HEALTH_BAR_WIDTH*(health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int)pos.x + (width-HEALTH_BAR_WIDTH)/2, (int)pos.y - 5, HEALTH_BAR_WIDTH, 5);
    }
}
