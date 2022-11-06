package com.mcdead.wallpass;

import java.io.IOException;
import java.util.Properties;

public class GameSettings implements GameSettingsModifiable {
    public final static String C_DEFAULT_SETTINGS_FILENAME = "settings.properties";
    public final static String C_SPEED_PROP_NAME      = "speed";
    public final static String C_DIFFICULTY_PROP_NAME = "difficulty";

    public final static double     C_DEFAULT_SPEED      = 0.1;
    public final static Difficulty C_DEFAULT_DIFFICULTY = Difficulty.EASY;

    private static GameSettings m_instance;

    private double     m_speed;
    private Difficulty m_difficulty;

    private GameSettings() {
        m_speed      = C_DEFAULT_SPEED;
        m_difficulty = C_DEFAULT_DIFFICULTY;
    }

    public static boolean initialize() throws IOException {
        if (m_instance != null) return true;

        m_instance = new GameSettings();

        Properties loadedProps = PropertiesFileLoaderStorer.load(C_DEFAULT_SETTINGS_FILENAME);

        return loadedProps.isEmpty() ? true : m_instance.setWithProperties(loadedProps);
    }

    public static GameSettingsReadable getInstance() {
        return (GameSettingsReadable) m_instance;
    }

    @Override
    public boolean setSpeed(double newSpeed) {
        if (newSpeed <= 0) return false;

        m_speed = newSpeed;

        return true;
    }

    @Override
    public boolean setDifficulty(Difficulty newDifficulty) {
        if (newDifficulty == null) return false;

        m_difficulty = newDifficulty;

        return true;
    }

    @Override
    public boolean setWithProperties(Properties properties) {
        if (!properties.containsKey(C_SPEED_PROP_NAME)
         || !properties.containsKey(C_DIFFICULTY_PROP_NAME))
        {
            return false;
        }

        double     speed      = Double.parseDouble(properties.getProperty(C_SPEED_PROP_NAME));
        Difficulty difficulty = Difficulty.getDifficultyByType(Integer.parseInt(properties.getProperty(C_DIFFICULTY_PROP_NAME)));

        return setSpeed(speed) && setDifficulty(difficulty);
    }

    @Override
    public double getSpeed() {
        return m_speed;
    }

    @Override
    public Difficulty getDifficulty() {
        return m_difficulty;
    }

    @Override
    public Properties getAsProperties() {
        Properties settingsProps = new Properties();

        settingsProps.setProperty(C_SPEED_PROP_NAME,      String.valueOf(m_speed));
        settingsProps.setProperty(C_DIFFICULTY_PROP_NAME, String.valueOf(m_difficulty.getType()));

        return settingsProps;
    }

    public void save() throws IOException {
        PropertiesFileLoaderStorer.store(C_DEFAULT_SETTINGS_FILENAME, getAsProperties());
    }
}
