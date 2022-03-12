package model.entity;

import model.utility.KeyHandler;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public abstract class Entity {
    public Game gp;
    public KeyHandler keyH;
    public MouseHandler mouseH;
    public Position pos;
    public int height, width;
    public double speed, health;
}
