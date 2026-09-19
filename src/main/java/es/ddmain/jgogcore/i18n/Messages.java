package es.ddmain.jgogcore.i18n;

import java.util.Locale;

import es.ddmain.jgogcore.utils.ConfigManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Messages {

    private final MessageSource messageSource;
    private final ConfigManager configManager;
    @Getter
    private Locale locale;

    @PostConstruct
    void init() {
        this.locale = resolveLocale(configManager.getLang());
    }

    public void setLocale(String lang) {
        this.locale = resolveLocale(lang);
    }

    public String getString(String key, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }

    private static Locale resolveLocale(String lang) {
        return switch (lang == null ? "en" : lang.toLowerCase()) {
            case "es" -> Locale.of("es");
            case "fr" -> Locale.of("fr");
            default -> Locale.ENGLISH;
        };
    }
}