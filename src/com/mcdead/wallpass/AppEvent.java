package com.mcdead.wallpass;

public enum AppEvent {
    GAME_START(1), GAME_RESUME(2), GAME_RESTART(3),
    MENU_OPEN(4), MENU_BACK(5), MENU_OPEN_PAUSE(6), MENU_OPEN_SETTINGS(7),
    APP_EXIT(8);

    private int m_type;

    private AppEvent(final int type) {
        m_type = type;
    }
}
