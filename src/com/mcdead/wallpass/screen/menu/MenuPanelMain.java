package com.mcdead.wallpass.screen.menu;

import com.mcdead.wallpass.AppEvent;
import com.mcdead.wallpass.AppEventQueue;

import javax.swing.*;
import java.awt.*;

public class MenuPanelMain extends MenuPanel {
    private final static String C_DEFAULT_LABEL_TEXT = "WALL PASS.";
    MenuPanelMain(AppEventQueue appEventQueueRef) {
        super(appEventQueueRef);

        // layout creation:

        Dimension buttonSize = new Dimension(120, 50);

        JLabel gameLabel = new JLabel(C_DEFAULT_LABEL_TEXT);
        JButton playButton = createMenuButton("Play", buttonSize, event -> startGame());
        JButton settingsButton = createMenuButton("Settings", buttonSize, event -> openSettings());
        JButton exitButton = createMenuButton("Exit", buttonSize, event -> processAppExit());

        gameLabel.setFont(new Font("Roboto", Font.BOLD, 32));

        JPanel buttonsPanel = createComponentsVerticalPanel(gameLabel, playButton, settingsButton, exitButton);

        EventQueue.invokeLater(() -> {
            add(buttonsPanel, BorderLayout.CENTER);
        });
    }

    public void startGame() {
        m_appEventQueueRef.put(AppEvent.GAME_START);
    }

    public void openSettings() {
        m_appEventQueueRef.put(AppEvent.MENU_OPEN_SETTINGS);
    }

    public void processAppExit() {
        m_appEventQueueRef.put(AppEvent.APP_EXIT);
    }
}
