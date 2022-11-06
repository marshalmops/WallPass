package com.mcdead.wallpass.screen.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class GameMap {
    public static final Dimension C_DEFAULT_SIZE = new Dimension(640, 480);

    private ArrayList<GameObject> m_gameObjects;
    private Dimension m_size;

    public GameMap() {
        m_gameObjects = new ArrayList<>();
        m_size = new Dimension(C_DEFAULT_SIZE);
    }

    public Iterator<GameObject> getGameObjectsIterator() {
        return m_gameObjects.iterator();
    }

    public boolean addGameObject(final GameObject newObject) {
        if (newObject == null) return false;

        m_gameObjects.add(newObject);

        return true;
    }

    public boolean removeGameObject(final int objectId) {
        for (final GameObject curObject : m_gameObjects) {
            if (curObject.getId() == objectId) {
                m_gameObjects.remove(curObject);

                return true;
            }
        }

        return false;
    }

    public Dimension getSize() {
        return m_size;
    }

    public boolean setSize(final Dimension size) {
        if (size.getHeight() <= 0 || size.getWidth() <= 0) return false;

        m_size = size;

        return true;
    }
}
