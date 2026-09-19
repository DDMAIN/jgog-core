package es.ddmain.jgogcore.db;

import es.ddmain.jgogcore.i18n.Messages;
import es.ddmain.jgogcore.utils.AppPaths;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class DatabaseManager {

    private static final String SCHEMA_NAME = "schema.sql";

    public static synchronized void initialize(Messages messages) throws SQLException, IOException {

        ClassPathResource resource = new ClassPathResource(SCHEMA_NAME);
        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:" + AppPaths.getDataBasePath()
                .toAbsolutePath().normalize().toString().replace('\\','/'), "sa", "");
             Statement stmt = conn.createStatement()) {

            String[] sentences = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8)
                    .split(";");

            for (String sentence : sentences) {
                sentence = sentence.trim();
                if (!sentence.isEmpty() && !sentence.startsWith("--") && !sentence.startsWith("/*")) {
                    try {
                        stmt.execute(sentence);
                    } catch (SQLException e) {
                        System.out.println(messages.getString("ddbb.error"));
                        throw e;
                    }
                }
            }
            System.out.println(messages.getString("ddbb.init.ok"));
        }

    }
}