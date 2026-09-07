package es.ddmain.jgogcore;

import es.ddmain.jgogcore.factory.SpringBeanFactory;
import es.ddmain.jgogcore.i18n.Messages;
import es.ddmain.jgogcore.utils.ConfigManager;
import es.ddmain.jgogcore.utils.VersionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import picocli.CommandLine;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class JGOGCoreApplication implements CommandLineRunner {

	private final BuildProperties buildProperties;
	private final RootCommand rootCommand;
	private final SpringBeanFactory springBeanFactory;
	private final Messages messages;

	public JGOGCoreApplication(BuildProperties buildProperties, RootCommand rootCommand,
							   SpringBeanFactory springBeanFactory, Messages messages) {
		this.buildProperties = buildProperties;
		this.rootCommand = rootCommand;
		this.springBeanFactory = springBeanFactory;
		this.messages = messages;
	}

	public static void main(String[] args) {

		System.setProperty("PICOCLI_COLORS", "false");

		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
		System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err), true, StandardCharsets.UTF_8));

		SpringApplication app = new SpringApplication(JGOGCoreApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		VersionUtil.init(buildProperties);

		Locale.setDefault(messages.getLocale());

		CommandLine cli = new CommandLine(rootCommand, springBeanFactory);
		cli.setColorScheme(CommandLine.Help.defaultColorScheme(CommandLine.Help.Ansi.OFF));

		if (args.length == 0) {
			cli.usage(System.out, CommandLine.Help.Ansi.OFF);
			return;
		}

		cli.execute(args);
	}
}
