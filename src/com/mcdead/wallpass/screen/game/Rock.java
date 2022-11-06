package com.mcdead.wallpass.screen.game;

import java.awt.image.BufferedImage;

public class Rock extends Barrier {
    public static final int C_DEFAULT_WIDTH  = 20;
    public static final int C_DEFAULT_HEIGHT = 20;

    public Rock(final int x, final int y) {
        super(new GameShape(x, y, C_DEFAULT_WIDTH, C_DEFAULT_HEIGHT), null);

        // todo: handle FALSE case:

        GameContext.setImageForObject(this);
    }


}
