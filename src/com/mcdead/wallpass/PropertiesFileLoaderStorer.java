package com.mcdead.wallpass;

import java.io.*;
import java.util.Properties;

public class PropertiesFileLoaderStorer {
    public static Properties load(final String filename) throws IOException {
        Properties loadedProps = new Properties();

        if (!(new File(filename)).exists())
            return loadedProps;

        FileReader reader = new FileReader(filename);

        loadedProps.load(reader);

        return loadedProps;
    }

    public static void store(final String filename,
                             final Properties props) throws IOException {
        FileWriter writer = new FileWriter(filename);

        props.store(writer, "");
    }
}
