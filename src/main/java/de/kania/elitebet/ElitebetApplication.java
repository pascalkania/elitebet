package de.kania.elitebet;

import de.kania.elitebet.properties.FootballDataProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableConfigurationProperties(FootballDataProperties.class)
public class ElitebetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElitebetApplication.class, args);
	}
}
