package es.ddmain.jgogcore.enums;

import java.util.Arrays;

public enum Lang {

    EN("en"),
    ES("es"),
    FR("fr");

    Lang(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static boolean isValid(final String langSearch) {
        return Arrays.stream(values()).anyMatch(lang -> lang.getValue().equals(langSearch));
    }
}
