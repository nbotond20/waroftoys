package com.runtimeexception.waroftoys.model.entity.tower;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.entity.Entity;
import com.runtimeexception.waroftoys.model.entity.unit.Unit;
import com.runtimeexception.waroftoys.model.player.Player;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Tower extends Entity {
    private static final boolean DRAW_IMAGE = true;
    private BufferedImage image;
    public Color color;

    public Tower(final Game game, final MouseHandler mouseH) {
        this.game = game;
        this.mouseH = mouseH;
        this.width = game.tileSize * game.scale;
        this.height = game.tileSize * game.scale;
        HEALTH_BAR_WIDTH = 50;
    }

    public Boolean useAbility(final Unit unit, final Boolean own, final Player enemy) {
        // System.out.println("using ability");
        if (this.type == "ATTACK" && !own) {
            unit.health -= .28;
            if (unit.health <= 0) {
                enemy.unitDone.add(unit);
                return true;
            }
        }
        if (this.type == "FASTER" && own) {
            unit.speed = unit.REGULAR_SPEED * 1.5;
        }
        if (this.type == "SLOWER" && !own) {
            unit.speed = unit.REGULAR_SPEED * .7;
        }
        return false;
    }

    public Boolean isInRange(final Entity unit) {
        // System.out.println("isInRange");

        char type = 'N';

        if (unit.pos.x <= this.pos.x && unit.pos.y <= this.pos.y)
            type = 'A';
        if (unit.pos.x >= this.pos.x && unit.pos.y <= this.pos.y)
            type = 'B';
        if (unit.pos.x <= this.pos.x && unit.pos.y >= this.pos.y)
            type = 'C';
        if (unit.pos.x >= this.pos.x && unit.pos.y >= this.pos.y)
            type = 'D';

        final double rad = Math.sqrt(Math.pow((unit.pos.x - this.pos.x), 2) + Math.pow((unit.pos.y - this.pos.y), 2));

        // System.out.println(rad + " < calc | range > " + this.range);
        if (rad <= this.range / 2)
            return true;

        return false;
    }

    public void getTowerImage(final String filename) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/towers/" + filename + ".png"));
        } catch (final IOException e) {
            System.out.println("entity.Tower.getTowerImage()");
        }
    }

    public void draw(final Graphics2D g2) {
        if (game.hoverPosition.x == pos.x && game.hoverPosition.y == pos.y) {
            g2.setColor(new Color(100, 100, 100, 100));
            g2.fillOval((int) (pos.x + width / 2 - range / 2), (int) (pos.y + width / 2 - range / 2), (int) range,
                    (int) range);
            g2.setColor(color);
            g2.drawOval((int) (pos.x + width / 2 - range / 2), (int) (pos.y + width / 2 - range / 2), (int) range,
                    (int) range);
        }

        if (DRAW_IMAGE) {
            g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null);
        } else {
            g2.setColor(color);
            g2.fillOval((int) pos.x, (int) pos.y, width, height);
        }

        g2.setColor(Color.RED);
        g2.fillRect((int) pos.x + (width - HEALTH_BAR_WIDTH) / 2, (int) pos.y - 5,
                (int) (HEALTH_BAR_WIDTH * (health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int) pos.x + (width - HEALTH_BAR_WIDTH) / 2, (int) pos.y - 5, HEALTH_BAR_WIDTH, 5);
    }
}
