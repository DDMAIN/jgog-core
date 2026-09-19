package es.ddmain.jgogcore.utils;

import org.springframework.boot.info.BuildProperties;

public final class VersionUtil {

    private static BuildProperties buildProperties;

    public static void init(BuildProperties buildPropertiesInstance) {
        buildProperties = buildPropertiesInstance;
    }

    public static String get() {
        return buildProperties != null && buildProperties.getVersion() != null
                ? buildProperties.getVersion()
                : "DEV";
    }

    private VersionUtil() {}
}
