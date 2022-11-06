package com.mcdead.wallpass.screen.game;

import java.awt.image.BufferedImage;

public class Box extends Barrier implements MovableInterface {
    public static final int C_DEFAULT_WIDTH  = 20;
    public static final int C_DEFAULT_HEIGHT = 20;
    public final static int C_BOX_SPEED = 3;

    public Box(final int x, final int y) {
        super(new GameShape(x, y, C_DEFAULT_WIDTH, C_DEFAULT_HEIGHT), null);

        // todo: handle FALSE case:

        GameContext.setImageForObject(this);
    }

    @Override
    public boolean move(int dx, int dy) {
        return getShape().changeX(dx) && getShape().changeY(dy);
    }

    @Override
    public int getSpeed() {
        return C_BOX_SPEED;
    }

}
