package com.mcdead.wallpass.screen;

import com.mcdead.wallpass.AppEventQueue;

import javax.swing.*;

public abstract class Screen extends JPanel {
    protected AppEventQueue m_appEventQueueRef;

    public Screen(AppEventQueue appEventQueueRef) {
        m_appEventQueueRef = appEventQueueRef;
    }

    public abstract void setFullEnabled();
    public abstract void prepareToShow();
}
