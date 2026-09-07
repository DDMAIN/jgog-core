package es.ddmain.jgogcore.commands;

import es.ddmain.jgogcore.db.DatabaseManager;
import es.ddmain.jgogcore.enums.Lang;
import es.ddmain.jgogcore.i18n.Messages;
import es.ddmain.jgogcore.utils.AppPaths;
import es.ddmain.jgogcore.utils.ConfigManager;
import es.ddmain.jgogcore.utils.VersionUtil;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "init",
        mixinStandardHelpOptions = true,
        description = "Start and configure the system"
)
public class InitCommand implements Callable<Integer> {

    private final Messages messages;
    private final ConfigManager configManager;


    public InitCommand(Messages messages, ConfigManager configManager){
        this.messages = messages;
        this.configManager = configManager;
    }


    @Override
    public Integer call() {

        int returnValue = 0;

        if (Files.notExists(AppPaths.getConfigFile())) {
            returnValue = initSystem();
        } else {
            System.out.println(messages.getString("command.init.already.initialized"));
        }

        return returnValue;
    }

    private Integer initSystem() {

        System.out.println(messages.getString("command.init.init"));

        String lang = selectUserLanguage();

        try {
            createDirectories();
        } catch (IOException e) {
            return -1;
        }

        return initConfigFile(lang);

    }

    private Integer initConfigFile(String lang) {

        if (generateConfigFile(configManager, lang) == -1) {
            return -1;
        }

        return initDataBase(configManager);
    }

    private Integer initDataBase(ConfigManager configManager) {
        try {
            DatabaseManager.initialize(messages);
            System.out.println(messages.getString("command.init.ok"));
            return 0;
        } catch (SQLException | IOException e) {
            deleteConfiguration(configManager);
            return -1;
        }
    }

    private Integer generateConfigFile(ConfigManager configManager, String lang) {
        int returnValue = 0;
        boolean deleting = false;

        final String appVersion = VersionUtil.get();

        if (configManager.setVersion(appVersion)) {
            if (!configManager.setLang(lang)) {
                returnValue = -1;
                deleting = true;
                deleteConfiguration(configManager);
            }
        } else {
            returnValue = -1;
        }

        if (returnValue == -1 && !deleting) {
            deleteConfiguration(configManager);
        }

        return returnValue;
    }

    private void deleteConfiguration(ConfigManager configManager) {
        if (!configManager.deleteConfig()) {
            System.out.println(MessageFormat.format(messages.getString("command.init.serious_error"), AppPaths.getConfigFile()));
        }
    }

    private String selectUserLanguage() {
        boolean langValid = false;
        Scanner scanner = new Scanner(System.in);
        String lang;
        do {
            System.out.print(messages.getString("command.init.select_language") + " (en/es/fr): ");
            lang = scanner.nextLine().trim();
            if (!Lang.isValid(lang)) {
                System.out.println(MessageFormat.format(messages.getString("command.init.language_no_.supported"), lang));
            } else {
                langValid = true;
            }

        } while (!langValid);

        messages.setLocale(lang);

        return lang;
    }

    private void createDirectories() throws IOException {
        System.out.println(messages.getString("command.init.create_folders"));
        Files.createDirectories(AppPaths.getAppDirectory());
        Files.createDirectories(AppPaths.getDataDirectory());
        Files.createDirectories(AppPaths.getLogsDirectory());
    }
}
