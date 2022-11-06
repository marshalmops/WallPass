package com.mcdead.wallpass.screen.game;

import com.mcdead.wallpass.AppEvent;
import com.mcdead.wallpass.AppEventQueue;
import com.mcdead.wallpass.GameSettings;
import com.mcdead.wallpass.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class GameScreen extends Screen {
    private AtomicBoolean m_isGameCycleAlive;
    private AtomicBoolean m_isGameCyclePaused;
    private FutureTask<Boolean> m_processingFuture;

    private GameState m_gameState;

    public GameScreen(AppEventQueue appEventQueueRef) {
        super(appEventQueueRef);

        m_isGameCycleAlive  = new AtomicBoolean(false);
        m_isGameCyclePaused = new AtomicBoolean(false);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            if (!m_isGameCycleAlive.get() || m_isGameCyclePaused.get())
                return false;

            Player player = m_gameState.getPlayer();

            synchronized (player) {
                if (event.getID() == KeyEvent.KEY_PRESSED) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_A -> player.setMoveDirection(MoveDirection.LEFT);
                        case KeyEvent.VK_D -> player.setMoveDirection(MoveDirection.RIGHT);
                    }
                } else if (event.getID() == KeyEvent.KEY_RELEASED) {
                    switch (event.getKeyCode()) {
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_D:      {player.setMoveDirection(MoveDirection.NOWHERE); break;}
                        case KeyEvent.VK_ESCAPE: {pause(); m_appEventQueueRef.put(AppEvent.MENU_OPEN_PAUSE);}
                    }
                }
            }

            return false;
        });
    }

    public void start() {
        m_isGameCycleAlive.set(true);
        m_isGameCyclePaused.set(false);

        m_processingFuture = new FutureTask<>(() -> {
            m_gameState = new GameState();

            while (m_isGameCycleAlive.get()) {
                int timeout = (int) (GameContext.C_DEFAULT_FRAME_TIME / GameSettings.getInstance().getSpeed());

                Thread.sleep(timeout);

                if (!m_isGameCyclePaused.get()) {
                    gameIteration();
                }
            }

            return true;
        });

        new Thread(m_processingFuture).start();
    }

    public void stop() {
        m_isGameCycleAlive.set(false);
    }

    public void pause() {
        m_isGameCyclePaused.set(true);
    }

    public void resume() {
        m_isGameCyclePaused.set(false);
    }

    public void restart() throws ExecutionException, InterruptedException {
        stop();

        m_processingFuture.get();

        start();
    }

    private void gameIteration() {
        // decor moving:

        Iterator<GameObject> gameObjectsIter = m_gameState.getGameMap().getGameObjectsIterator();

        ArrayList<Integer> objIdToRemove = new ArrayList<>();

        while (gameObjectsIter.hasNext()) {
            GameObject curObj = gameObjectsIter.next();

            if (curObj instanceof MovableInterface) {
                MovableInterface movableObj = (MovableInterface) curObj;

                if (curObj.getShape().getY() + movableObj.getSpeed() >= m_gameState.getGameMap().getSize().getHeight())
                    objIdToRemove.add(curObj.getId());
                else
                    curObj.getShape().changeY(movableObj.getSpeed());
            }
        }

        if (!objIdToRemove.isEmpty()) {
            objIdToRemove.forEach((id) -> m_gameState.getGameMap().removeGameObject(id));
            m_gameState.generateBarriersWithDifficulty();
        }

        // person moving:

        Player player = m_gameState.getPlayer();

        synchronized (player) {
            switch (player.getMoveDirection()) {
                case RIGHT -> player.move(Player.C_DEFAULT_DX, 0);
                case LEFT  -> player.move(-Player.C_DEFAULT_DX, 0);
                default -> {}
            }
        }

        repaint();

        // touch check:

        Iterator<GameObject> gameObjectIterator = m_gameState.getGameMap().getGameObjectsIterator();

        while (gameObjectIterator.hasNext()) {
            GameObject curObject = gameObjectIterator.next();

            if (!(curObject instanceof TouchableInterface))
                continue;

            if (player.touched(curObject)) {
                processGameEnd();

                return;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        GameFrameGenerator.GameFrame newGameFrame = new GameFrameGenerator.GameFrame();

        if (!GameFrameGenerator.generateFrame(m_gameState, newGameFrame))
            return;

        graphics.drawImage(newGameFrame.getFrameImg(), 0, 0, getWidth(), getHeight(), null);
    }

    private void processGameEnd() {
        m_isGameCycleAlive.set(false);

        // todo: change it to showing a panel with RESTART and TO NENU buttons;

        JOptionPane.showMessageDialog(this, "You have lost!");

        m_appEventQueueRef.put(AppEvent.MENU_OPEN);
    }

    @Override
    public void setFullEnabled() {

    }

    @Override
    public void prepareToShow() {

    }
}
