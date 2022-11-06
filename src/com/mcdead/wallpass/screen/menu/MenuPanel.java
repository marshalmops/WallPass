package com.mcdead.wallpass.screen.menu;

import com.mcdead.wallpass.AppEventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MenuPanel extends JPanel {
    protected AppEventQueue m_appEventQueueRef;

    MenuPanel(AppEventQueue appEventQueueRef) {
        m_appEventQueueRef = appEventQueueRef;
    }

    public static JButton createMenuButton(final String labelText,
                                           final Dimension prefSize,
                                           final ActionListener actionFunc)
    {
        return new JButton(labelText) {{setSize(prefSize); addActionListener(actionFunc);}};
    }

    public static JPanel createComponentsVerticalPanel(final Component... components) {
        JPanel componentsPanel = new JPanel();

        GridLayout componentsPanelLayout = new GridLayout(components.length, 1);

        EventQueue.invokeLater(() -> {
            componentsPanel.setLayout(componentsPanelLayout);

            for (int i = 0; i < components.length; ++i)
                componentsPanel.add(components[i]);
        });

        return componentsPanel;
    }
}
