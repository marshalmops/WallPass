package com.mcdead.wallpass;

import java.util.Properties;

public interface GameSettingsReadable {
    double     getSpeed       ();
    Difficulty getDifficulty  ();
    Properties getAsProperties();
}
