package com.mcdead.wallpass.screen.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    private static int m_idCounter = 0;

    private int m_id;
    private GameShape m_shape;
    private BufferedImage m_image;

    public GameObject(final GameShape shape,
                      final BufferedImage image)
    {
        m_id = m_idCounter;
        m_shape = shape;
        m_image = image;

        ++m_idCounter;
    }

    public int getId() {
        return m_id;
    }

    public boolean setShape(final GameShape shape) {
        if (!shape.isValid()) return false;

        m_shape = shape;

        return true;
    }

    public GameShape getShape() {
        return m_shape;
    }

    public boolean setImage(final BufferedImage image) {
        if (image.getWidth() <= 0 || image.getHeight() <= 0) return false;

        m_image = image;

        return true;
    }

    public BufferedImage getImage() {
        return m_image;
    }
}
