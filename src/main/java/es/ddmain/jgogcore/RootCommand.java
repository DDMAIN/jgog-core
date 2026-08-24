package es.ddmain.jgogcore;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "jgogcore",
        description = "JGOGCore CLI - GOG Manager",
        mixinStandardHelpOptions = true,
        version = "0.0.1"
)
public class RootCommand implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("=== JGOGCore CLI ===");
        System.out.println("Use a subcommand: auth, download, compare, ...");
        System.out.println("Type '--help' to see the available options.");
        return 0;
    }
}
