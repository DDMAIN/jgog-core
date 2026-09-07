package es.ddmain.jgogcore.utils;

import java.nio.file.Path;

public class AppPaths {
    private static final String APP_DIRECTORY_NAME = ".jgogcore";
    private static final String DATA_DIRECTORY_NAME = "data";
    private static final String LOGS_DIRECTORY_NAME = "logs";

    private static final String CONFIG_FILENAME = "config.json";
    private static final String DATABASE_NAME = "jgogcore";

    private AppPaths (){

    }

    public static Path getHomeDirectory() {
        return Path.of(System.getProperty("user.home"));
    }

    public static Path getAppDirectory() {
        return getHomeDirectory().resolve(APP_DIRECTORY_NAME);
    }

    public static Path getDataDirectory() {
        return getHomeDirectory().resolve(DATA_DIRECTORY_NAME);
    }

    public static Path getLogsDirectory() {
        return getHomeDirectory().resolve(LOGS_DIRECTORY_NAME);
    }

    public static Path getConfigFile() {
        return getAppDirectory().resolve(CONFIG_FILENAME);
    }

    public static Path getDataBasePath() {
        return getAppDirectory().resolve(DATABASE_NAME);
    }
}
