package nimblix.in.HealthCareHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class HealthCareHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareHubApplication.class, args);
	}

}
