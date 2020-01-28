package pokapi;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAdminServer
public class Pokapi {

	public static void main(String[] args) {
		SpringApplication.run(Pokapi.class, args);

	}

}

