package activitytrackerspringbootsolution;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class ActivitytrackerSpringbootSolutionApplication {

//    @PersistenceContext
//    private EntityManager manager;

    public static void main(String[] args) {
        SpringApplication.run(ActivitytrackerSpringbootSolutionApplication.class, args);

    }

//    @Bean
//    @Scope(value = "prototype")
//
//    public ActivityDao activityDao() {
//        return new ActivityDao(manager);
//    }
}
