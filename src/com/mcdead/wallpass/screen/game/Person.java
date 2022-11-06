package com.mcdead.wallpass.screen.game;

import java.awt.image.BufferedImage;

public abstract class Person extends GameObject
        implements TouchableInterface, MovableInterface
{
    private int m_hp;
    private MoveDirection m_moveDirection;

    public Person(int x, int y,
                  int width, int height,
                  BufferedImage image,
                  final int hp)
    {
        super(new GameShape(x, y, width, height), image);

        m_hp = hp;
        m_moveDirection = MoveDirection.NOWHERE;
    }

    public int getHp() {
        return m_hp;
    }

    public void changeHp(final int dhp) {
        m_hp += dhp;
    }

    public MoveDirection getMoveDirection() {
        return m_moveDirection;
    }

    public boolean setMoveDirection(final MoveDirection direction) {
        if (direction == null) return false;

        m_moveDirection = direction;

        return true;
    }

    @Override
    public boolean touched(GameObject other) {
        return getShape().intersectsWith(other.getShape());
    }

    @Override
    public boolean move(int dx, int dy) {
        return getShape().changeX(dx) && getShape().changeY(dy);
    }

}
