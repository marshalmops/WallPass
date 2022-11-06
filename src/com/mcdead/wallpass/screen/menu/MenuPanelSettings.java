package com.mcdead.wallpass.screen.menu;

import com.mcdead.wallpass.*;

import javax.swing.*;
import java.awt.*;

public class MenuPanelSettings extends MenuPanel implements SubmenuInterface {
    private final static int C_SPEED_MULTIPLIER = 10;

    private JSlider               m_speedSlider;
    private JComboBox<Difficulty> m_difficultyComboBox;

    MenuPanelSettings(AppEventQueue appEventQueueRef) {
        super(appEventQueueRef);

        GameSettingsReadable settings = GameSettings.getInstance();

        int speedInt          = (int) (settings.getSpeed() * C_SPEED_MULTIPLIER);
        int curDifficultyType = settings.getDifficulty().getType();

        m_speedSlider        = new JSlider(1, 10, speedInt);
        m_difficultyComboBox = new JComboBox<>(Difficulty.values());

        m_speedSlider.setPaintTicks(true);
        m_speedSlider.setMajorTickSpacing(1);
        m_speedSlider.setSnapToTicks(true);
        m_difficultyComboBox.setEditable(false);
        m_difficultyComboBox.setSelectedIndex(curDifficultyType);

        JLabel speedLabel      = new JLabel("Speed");
        JLabel difficultyLabel = new JLabel("Difficulty");

        // layout creation:

        JPanel settingsControlsPanel = new JPanel(new GridLayout(2, 2));

        settingsControlsPanel.add(speedLabel);
        settingsControlsPanel.add(m_speedSlider);
        settingsControlsPanel.add(difficultyLabel);
        settingsControlsPanel.add(m_difficultyComboBox);

        Dimension buttonSize = new Dimension(120, 50);

        JButton applyButton = createMenuButton("Apply", buttonSize, event -> applyChanges());
        JButton backButton  = createMenuButton("Back",  buttonSize, event -> moveBack());

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));

        buttonsPanel.add(applyButton);
        buttonsPanel.add(backButton);

        JPanel mainPanel = createComponentsVerticalPanel(settingsControlsPanel, buttonsPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void applyChanges() {
        double     speed      = (double)m_speedSlider.getValue() / C_SPEED_MULTIPLIER;
        Difficulty difficulty = m_difficultyComboBox.getItemAt(m_difficultyComboBox.getSelectedIndex());

        GameSettings settings = (GameSettings)GameSettings.getInstance();

        if (!settings.setSpeed(speed) || !settings.setDifficulty(difficulty)) {
            JOptionPane.showMessageDialog(this, "Provided settings are invalid!");

            return;
        }

        try {
            settings.save();
            JOptionPane.showMessageDialog(this, "Settings has been saved!");

        } catch (Throwable e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(this, "Settings saving error!");
        }
    }

    @Override
    public void moveBack() {
        m_appEventQueueRef.put(AppEvent.MENU_BACK);
    }
}
