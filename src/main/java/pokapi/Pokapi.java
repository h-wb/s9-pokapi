package pokapi;

import de.codecentric.boot.admin.client.registration.Application;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableAdminServer
public class Pokapi {

	@SuppressWarnings("checkstyle:LineLength")
	public static void main(String[] args) {
		SpringApplication.run(Pokapi.class, args);
//		SpringApplicationBuilder parentBuilder = new SpringApplicationBuilder(Pokapi.class);
//		parentBuilder.child(ServiceOneConfiguration.class).properties("server.port:8080").run(args);
//		parentBuilder.child(ServiceTwoConfiguration.class).properties("server.port:8090").run(args);
	}

//	@Service
//	static class SharedService {
//		public String getMessage(String name) {
//			return String.format("Hello, %s, I'm shared service", name);
//		}
//	}
//
//	@Configuration
//	@EnableAutoConfiguration
//	static class ServiceOneConfiguration {
//		@Controller
//		@RequestMapping("/server")
//		static class ControllerOne {
//			@Autowired
//			private SharedService service;
//
//			@RequestMapping(produces = "text/plain;charset=utf-8")
//			@ResponseBody
//			public String getMessage(String name) {
//				return "ControllerOne says \"" + service.getMessage(name) + "\"";
//			}
//		}
//	}
//
//	@Configuration
//	@EnableAutoConfiguration
//	static class ServiceTwoConfiguration {
//		@Bean
//		TomcatServletWebServerFactory servletContainer() {
//			TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//			tomcat.setUriEncoding(StandardCharsets.CP1251);
//			return tomcat;
//		}
//
//		@Controller
//		@RequestMapping("/client")
//		static class ControllerTwo {
//			@Autowired
//			private SharedService service;
//
//			@RequestMapping(produces = "text/plain;charset=utf-8")
//			@ResponseBody
//			public String getMessage(String name) {
//				return "ControllerTwo says \"" + service.getMessage(name) + "\"";
//			}
//		}
//	}
}

