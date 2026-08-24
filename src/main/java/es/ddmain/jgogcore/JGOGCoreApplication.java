package es.ddmain.jgogcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class JGOGCoreApplication implements CommandLineRunner {

	@Autowired
	private RootCommand rootCommand;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JGOGCoreApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		new CommandLine(rootCommand).execute(args);
	}
}
