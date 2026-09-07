package es.ddmain.jgogcore.commands;

import es.ddmain.jgogcore.i18n.Messages;
import es.ddmain.jgogcore.utils.VersionUtil;
import picocli.CommandLine;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "status",
        mixinStandardHelpOptions = true,
        description = "Show the state of the system"
)
public class StatusCommand implements Callable<Integer> {

    private final Messages messages;

    public StatusCommand(Messages messages) {
        this.messages = messages;
    }

    @Override
    public Integer call() {
        System.out.println(messages.getString("command.status.jgogcore"));
        System.out.println(MessageFormat.format(messages.getString("command.status.version"), VersionUtil.get()));
        System.out.println(messages.getString("command.status.ready"));
        return 0;
    }
}
