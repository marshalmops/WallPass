package com.mcdead.wallpass.screen.game;

public class Player extends Person {
    public static final int C_DEFAULT_WIDTH  = 30;
    public static final int C_DEFAULT_HEIGHT = 40;
    public static final int C_DEFAULT_HP = 1;
    public static final int C_DEFAULT_DX = 3;

    public Player(int x, int y) {
        super(x, y, C_DEFAULT_WIDTH, C_DEFAULT_HEIGHT, null, C_DEFAULT_HP);

        // todo: handle FALSE case:

        GameContext.setImageForObject(this);
    }

    @Override
    public int getSpeed() {
        return C_DEFAULT_DX;
    }
}
