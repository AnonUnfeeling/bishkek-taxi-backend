package ua.jdroidcoder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ua.jdroidcoder.config.StorageProperties;
import ua.jdroidcoder.service.OrderService;
import ua.jdroidcoder.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TaxiBishkekApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiBishkekApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
			storageService.deleteAll();
            storageService.init();
        };
    }
}
