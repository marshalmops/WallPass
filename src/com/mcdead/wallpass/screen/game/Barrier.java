package com.mcdead.wallpass.screen.game;

import java.awt.image.BufferedImage;

public abstract class Barrier extends GameObject
    implements TouchableInterface
{

    public Barrier(GameShape shape, BufferedImage image) {
        super(shape, image);
    }

    @Override
    public boolean touched(GameObject other) {
        return getShape().intersectsWith(other.getShape());
    }
}
