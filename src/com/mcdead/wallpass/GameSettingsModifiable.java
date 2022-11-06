package com.mcdead.wallpass;

import java.util.Properties;

public interface GameSettingsModifiable extends GameSettingsReadable {
    boolean setSpeed         (final double newSpeed);
    boolean setDifficulty    (final Difficulty newDifficulty);
    boolean setWithProperties(final Properties properties);
}
