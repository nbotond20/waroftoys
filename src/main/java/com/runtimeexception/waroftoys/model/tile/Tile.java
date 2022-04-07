package com.runtimeexception.waroftoys.model.tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image, secondaryImage;
    public boolean collision = false;
    public Integer[] dimension;
    public Integer[][] bitMap;
    public String type;
}