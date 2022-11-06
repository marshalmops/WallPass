package com.mcdead.wallpass.screen.game;

import com.mcdead.wallpass.Difficulty;
import com.mcdead.wallpass.GameSettings;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class GameState {
    public static final int C_DEFAULT_SCORE = 0;

    private int m_score;
    private GameMap m_map;
    private Player m_player;

    public GameState() {
        m_score = C_DEFAULT_SCORE;
        m_map = new GameMap();
        m_player = new Player(0, 0);

        int initPlayerX = m_map.getSize().width / 2 - m_player.getShape().getWidth() / 2;
        int initPlayerY = m_map.getSize().height - m_player.getShape().getHeight();

        m_player.getShape().setX(initPlayerX);
        m_player.getShape().setY(initPlayerY);

        generateWalls();
        generateBarriersWithDifficulty();
    }

    public boolean generateBarriersWithDifficulty() {
        Difficulty difficulty = GameSettings.getInstance().getDifficulty();

        if (difficulty == null) return false;

        // generating HOLES X coords for moving walls:

        Random randGenerator = new Random(System.currentTimeMillis());
        ArrayList<Integer> holesXCoords = new ArrayList<>();

        final int minX = Box.C_DEFAULT_WIDTH + difficulty.getHoleRadius();
        final int maxX = m_map.getSize().width - Box.C_DEFAULT_WIDTH - difficulty.getHoleRadius();

        for (int i = 0; i < difficulty.getHolesCount(); ++i) {
            int curHoleX;

            while (true) {
                curHoleX = randGenerator.nextInt(minX, maxX);
                boolean isIncorrectGot = false;

                for (final Integer holeX : holesXCoords) {
                    if (Math.abs(holeX - curHoleX) < Box.C_DEFAULT_WIDTH * 2)
                        isIncorrectGot = true;
                }

                if (isIncorrectGot) continue;

                break;
            }

            holesXCoords.add(curHoleX);
        }

        // generating moving walls:

        for (int curX = Box.C_DEFAULT_WIDTH; curX < m_map.getSize().width - Box.C_DEFAULT_WIDTH; curX += Box.C_DEFAULT_WIDTH) {
            boolean placeFlag = false;

            for (final Integer curHoleX : holesXCoords) {
                if (Math.abs(curX - curHoleX) < Box.C_DEFAULT_WIDTH * difficulty.getHoleRadius()) {
                    placeFlag = true;

                    break;
                }
            }

            if (!placeFlag)
                m_map.addGameObject(new Box(curX, 0));
        }

        return true;
    }

    private void generateWalls() {
        final int leftWallX  = 0;
        final int rightWallX = m_map.getSize().width - Rock.C_DEFAULT_WIDTH;

        for (int curY = 0; curY < m_map.getSize().height; curY += Rock.C_DEFAULT_HEIGHT) {
            m_map.addGameObject(new Rock(leftWallX, curY));
            m_map.addGameObject(new Rock(rightWallX, curY));
        }
    }

    public int getScore() {
        return m_score;
    }

    public GameMap getGameMap() {
        return m_map;
    }

    public Player getPlayer() {
        return m_player;
    }
}
