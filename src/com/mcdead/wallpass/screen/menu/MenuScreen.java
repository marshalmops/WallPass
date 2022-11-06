package com.mcdead.wallpass.screen.menu;

import com.mcdead.wallpass.AppEventQueue;
import com.mcdead.wallpass.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MenuScreen extends Screen {
    private Stack<MenuPanel> m_menuPanelsStack;

    public MenuScreen(AppEventQueue appEventQueue) {
        super(appEventQueue);

        m_menuPanelsStack = new Stack<>();

        EventQueue.invokeLater(() -> {
            setLayout(new GridBagLayout());
        });
    }

    public void showMainMenu() {
        MenuPanel mainMenuPanel = new MenuPanelMain(m_appEventQueueRef);

        addMenuPanelToLayout(mainMenuPanel);
    }

    public void showPauseMenu() {
        MenuPanel mainMenuPanel = new MenuPanelPause(m_appEventQueueRef);

        addMenuPanelToLayout(mainMenuPanel);
    }

    public void showSettingsMenu() throws Exception {
        MenuPanel settingsMenuPanel = new MenuPanelSettings(m_appEventQueueRef);

        addMenuPanelToLayout(settingsMenuPanel);
    }

    public void showPreviousMenu() {
        m_menuPanelsStack.pop();

        MenuPanel prevPanel = m_menuPanelsStack.pop();

        addMenuPanelToLayout(prevPanel);
    }

    private void addMenuPanelToLayout(MenuPanel menuPanel) {
        EventQueue.invokeLater(() -> {
            removeAll();
            repaint();
            revalidate();

            GridBagConstraints spacerContaints    = new GridBagConstraints();
            GridBagConstraints mainPanelContaints = new GridBagConstraints();

            spacerContaints.weighty  = 1;
            mainPanelContaints.gridy = 1;

            add(new Container(), spacerContaints);
            add(menuPanel, mainPanelContaints);

            spacerContaints.gridy = 2;

            add(new Container(), spacerContaints);
        });

        m_menuPanelsStack.add(menuPanel);
    }

    @Override
    public void setFullEnabled() {

    }

    @Override
    public void prepareToShow() {
        m_menuPanelsStack.clear();
    }
}
