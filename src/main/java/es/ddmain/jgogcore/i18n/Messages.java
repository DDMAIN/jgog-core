package es.ddmain.jgogcore.i18n;

import java.util.Locale;

import es.ddmain.jgogcore.utils.ConfigManager;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;

@Component
public class Messages {

    private final MessageSource messageSource;
    private final ConfigManager configManager;
    private Locale locale;

    public Messages(MessageSource messageSource, ConfigManager configManager) {
        this.messageSource = messageSource;
        this.configManager = configManager;
    }

    @PostConstruct
    void init() {
        this.locale = resolveLocale(configManager.getLang().get());
    }

    public void setLocale(String lang) {
        this.locale = resolveLocale(lang);
    }

    public String getString(String key, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }

    public Locale getLocale() {
        return locale;
    }

    private static Locale resolveLocale(String lang) {
        return switch (lang == null ? "en" : lang.toLowerCase()) {
            case "es" -> Locale.of("es");
            case "fr" -> Locale.of("fr");
            default -> Locale.ENGLISH;
        };
    }
}