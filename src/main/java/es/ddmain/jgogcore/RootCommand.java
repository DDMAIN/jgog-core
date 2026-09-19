package es.ddmain.jgogcore;

import es.ddmain.jgogcore.commands.DBCommand;
import es.ddmain.jgogcore.commands.InitCommand;
import es.ddmain.jgogcore.commands.StatusCommand;
import es.ddmain.jgogcore.utils.VersionUtil;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "jgogcore",
        description = "JGOGCore CLI - GOG Manager",
        mixinStandardHelpOptions = true,
        versionProvider = RootCommand.VersionProviderImpl.class,
        resourceBundle = "i18n/messages",
        subcommands = {
                InitCommand.class,
                StatusCommand.class,
                DBCommand.class
        }
)
public class RootCommand implements Callable<Integer>, CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() {
        return new String[]{VersionUtil.get()};
    }

    @Override
    public Integer call() {
        CommandLine.Help.Ansi ansi = CommandLine.Help.Ansi.OFF;
        CommandLine.Help.ColorScheme colorScheme = CommandLine.Help.defaultColorScheme(ansi);

        CommandLine cli = new CommandLine(this);
        cli.setColorScheme(colorScheme);

        cli.usage(System.out);
        return 0;
    }

    public static class VersionProviderImpl implements CommandLine.IVersionProvider {
        @Override
        public String[] getVersion() {
            return new String[]{VersionUtil.get()};
        }
    }
}
