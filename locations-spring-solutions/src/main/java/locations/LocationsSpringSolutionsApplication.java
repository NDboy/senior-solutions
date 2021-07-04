package locations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocationsSpringSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationsSpringSolutionsApplication.class, args);
    }

    @Bean
    public LocationService locationService() {
        return new LocationService();
    }

}
