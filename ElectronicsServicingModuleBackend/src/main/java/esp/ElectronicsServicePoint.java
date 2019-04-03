package esp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "esp")
public class ElectronicsServicePoint {

	public static void main(String[] args) {
		log.info("Launching ElectronicsServicePoint...");
		SpringApplication.run(ElectronicsServicePoint.class, args);
	}
}
