package com.mcdead.wallpass;

import com.mcdead.wallpass.screen.Screen;
import com.mcdead.wallpass.screen.game.GameContext;
import com.mcdead.wallpass.screen.game.GameScreen;
import com.mcdead.wallpass.screen.menu.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final static Dimension C_DEFAULT_FRAME_SIZE = new Dimension(640, 480);
    private final static String C_FRAME_TITLE = "WallPass";

    private static AppEventQueue m_appEventsQueue = new AppEventQueue();

    private MenuScreen m_menuScreen;
    private GameScreen m_gameScreen;

    public static void main(String[] argv) throws Exception {
        // initialization:

        MainFrame mainFrame = new MainFrame();

        if (!GameSettings.initialize()) {
            JOptionPane.showMessageDialog(null, "Game settings initialization was failed!");

            System.exit(-1);
        }
        if (!GameContext.initialize()) {
            JOptionPane.showMessageDialog(null, "Game context initialization was failed!");

            System.exit(-1);
        }

        // app event handling cycle running:

        AppEvent appEvent;

        while (true) {
            if ((appEvent = m_appEventsQueue.take()) == null)
                continue;

            if (!mainFrame.handleAppEvent(appEvent)) {
                JOptionPane.showMessageDialog(null, "Event handling was failed");

                System.exit(-1);
            }
        }
    }

    private boolean handleAppEvent(final AppEvent eventToHandle) throws Exception {
        switch (eventToHandle) {
            case GAME_START         -> {showScreen(m_gameScreen); m_gameScreen.start();}
            case GAME_RESUME        -> {showScreen(m_gameScreen); m_gameScreen.resume();}
            case GAME_RESTART       -> {showScreen(m_gameScreen); m_gameScreen.restart();}
            case MENU_OPEN          -> {showScreen(m_menuScreen); m_gameScreen.stop(); m_menuScreen.showMainMenu();}
            case MENU_OPEN_PAUSE    -> {showScreen(m_menuScreen); m_menuScreen.showPauseMenu();}
            case MENU_BACK          -> {m_menuScreen.showPreviousMenu();}
            case MENU_OPEN_SETTINGS -> {m_menuScreen.showSettingsMenu();}
            case APP_EXIT           -> {System.exit(0);}
            default                 -> {return false;}
        }

        return true;
    }

    public MainFrame() {
        // creating layout:

        m_gameScreen = new GameScreen(m_appEventsQueue);
        m_menuScreen = new MenuScreen(m_appEventsQueue);

        m_menuScreen.showMainMenu();
        EventQueue.invokeLater(() -> add(m_menuScreen, BorderLayout.CENTER));

        EventQueue.invokeLater(() -> {
            setTitle(C_FRAME_TITLE);
            setSize(C_DEFAULT_FRAME_SIZE);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        });
    }

    public void showScreen(Screen screen) {
        screen.prepareToShow();
        EventQueue.invokeLater(() -> {
            getContentPane().removeAll();
            add(screen, BorderLayout.CENTER);

            repaint();
            revalidate();
        });
    }

}
