package com.mcdead.wallpass.screen.menu;

import com.mcdead.wallpass.AppEvent;
import com.mcdead.wallpass.AppEventQueue;

import javax.swing.*;
import java.awt.*;

public class MenuPanelPause extends MenuPanel{
    MenuPanelPause(AppEventQueue appEventQueueRef) {
        super(appEventQueueRef);

        // layout creation:

        Dimension buttonSize = new Dimension(120, 50);

        JButton resumeButton = createMenuButton("Resume", buttonSize, event -> resumeGame());
        JButton restartButton = createMenuButton("Restart", buttonSize, event -> restartGame());
        JButton settingsButton = createMenuButton("Settings", buttonSize, event -> openSettings());
        JButton mainMenuButton = createMenuButton("To Menu", buttonSize, event -> openMainMenu());

        JPanel buttonsPanel = createComponentsVerticalPanel(resumeButton, restartButton, settingsButton, mainMenuButton);

        EventQueue.invokeLater(() -> {
            add(buttonsPanel, BorderLayout.CENTER);
        });
    }

    public void resumeGame() {
        m_appEventQueueRef.put(AppEvent.GAME_RESUME);
    }

    public void restartGame() {
        m_appEventQueueRef.put(AppEvent.GAME_RESTART);
    }

    public void openSettings() {
        m_appEventQueueRef.put(AppEvent.MENU_OPEN_SETTINGS);
    }

    public void openMainMenu() {
        m_appEventQueueRef.put(AppEvent.MENU_OPEN);
    }
}
