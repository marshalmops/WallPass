package com.mcdead.wallpass.screen.game;

public class GameShape {
    private int m_x;
    private int m_y;
    private int m_width;
    private int m_height;

    public GameShape(final int x, final int y,
                     final int width, final int height)
    {
        m_x = x;
        m_y = y;
        m_width = width;
        m_height = height;
    }

    public boolean setX(final int x) {
        if (x < 0) return false;

        m_x = x;

        return true;
    }

    public boolean changeX(final int dx) {
        return setX(m_x + dx);
    }

    public int getX() {
        return m_x;
    }

    public boolean setY(final int y) {
        if (y < 0) return false;

        m_y = y;

        return true;
    }

    public boolean changeY(final int dy) {
        return setY(m_y + dy);
    }

    public int getY() {
        return m_y;
    }

    public int getWidth() {
        return m_width;
    }

    public int getHeight() {
        return m_height;
    }

    public boolean intersectsWith(final GameShape other) {
        if (other.m_x + other.m_width >= m_x && other.m_x + other.m_width <= m_x + m_width && other.m_y + other.m_height >= m_y && other.m_y + other.m_height <= m_y + m_height)
            return true;
        if (other.m_x <= m_x + m_width && other.m_x >= m_x && other.m_y + other.m_height >= m_y && other.m_y + other.m_height <= m_y + m_height)
            return true;
        if (other.m_x + other.m_width >= m_x && other.m_x + other.m_width <= m_x + m_width && other.m_y <= m_y + m_height && other.m_y >= m_y)
            return true;
        if (other.m_x >= m_x && other.m_x <= m_x + m_width && other.m_y >= m_y && other.m_y <= m_y + m_height)
            return true;

        return false;
    }

    public boolean isValid() {
        return m_width > 0 && m_height > 0 && m_x >= 0 && m_y >= 0;
    }
}
