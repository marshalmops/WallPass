package com.mcdead.wallpass.screen.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public abstract class GameFrameGenerator {
    public static class GameFrame {
        private BufferedImage m_frameImg;

        public GameFrame() { }

        public BufferedImage getFrameImg() {
            return m_frameImg;
        }

        public void setFrameImg(BufferedImage frameImg) {
            m_frameImg = frameImg;
        }
    }

    public static boolean generateFrame(final GameState gameState, GameFrame generatedFrame) {
        if (gameState == null) return false;

        GameMap gameMap = gameState.getGameMap();
        BufferedImage newFrame = new BufferedImage(gameMap.getSize().width, gameMap.getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics2D newFrameGraphics = newFrame.createGraphics();

        Iterator<GameObject> objectIterator = gameMap.getGameObjectsIterator();

        while (objectIterator.hasNext()) {
            GameObject curObject = objectIterator.next();

            newFrameGraphics.drawImage(curObject.getImage(), curObject.getShape().getX(), curObject.getShape().getY(), curObject.getShape().getWidth(), curObject.getShape().getHeight(), null);
        }

        Player player = gameState.getPlayer();

        newFrameGraphics.drawImage(player.getImage(), player.getShape().getX(), player.getShape().getY(), player.getShape().getWidth(), player.getShape().getHeight(), null);
        generatedFrame.setFrameImg(newFrame);

        return true;
    }
}
