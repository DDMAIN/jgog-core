package es.ddmain.jgogcore.utils;

import es.ddmain.jgogcore.enums.Lang;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ConfigManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Path configFile = AppPaths.getConfigFile();

    private Map<String, Object> config;

    public ConfigManager() throws IOException {
        loadConfig();
    }

    private void loadConfig() throws IOException {
        if (Files.exists(configFile)) {
            String json = Files.readString(configFile);
            config = mapper.readValue(json, new TypeReference<>() {
            });
        } else {
            config = new HashMap<>();
        }
    }

    private void saveConfig() throws IOException {
        Files.createDirectories(configFile.getParent());
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
        Files.writeString(configFile, json);
    }

    public String getLang() {
        Object lang = config.get("lang");
        if (!(lang instanceof String)) {
            return Lang.EN.getValue();
        }
        return (String) lang;
    }

    public boolean setLang(String lang) {
        config.put("lang", lang);
        try {
            saveConfig();
            return true;
        } catch (IOException e) {
            log.error("Error saving after assigning the language: "+ e.getMessage(), e );
            return false;
        }
    }

    public Optional<String> getVersion() {
        Object version = config.get("version");
        if (!(version instanceof String)) {
            return Optional.empty();
        }
        return Optional.of((String)version);
    }

    public boolean setVersion(String version) {
        config.put("version", version);
        try {
            saveConfig();
            return true;
        } catch (IOException e) {
            log.error("Error saving after assigning the version: "+ e.getMessage(), e );
            return false;
        }
    }


    public boolean deleteConfig() {
        try {
            Files.deleteIfExists(configFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}