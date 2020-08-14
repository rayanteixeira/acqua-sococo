package br.com.acqua;

import java.util.Locale;

import br.com.acqua.service.StorageService;
import br.com.acqua.service.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AcquaSococoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcquaSococoApplication.class, args);
	}

	@Bean
	public FixedLocaleResolver localeResolver() {

		return new FixedLocaleResolver(new Locale("pt", "BR"));

	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
