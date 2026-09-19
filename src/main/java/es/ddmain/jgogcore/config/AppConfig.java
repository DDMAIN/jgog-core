package es.ddmain.jgogcore.config;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.ddmain.jgogcore.utils.ConfigManager;

@Configuration
public class AppConfig {

    @Bean
    public ConfigManager configManager() {
        try {
            return new ConfigManager();
        } catch (IOException e) {
            throw new UncheckedIOException("Cannot load configuration", e);
        }
    }
}