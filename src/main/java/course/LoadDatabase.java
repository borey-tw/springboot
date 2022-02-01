package course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(course.LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CourseRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Course("Spring Boot intro 1", "Introduction about spring boot init data 1")));
            log.info("Preloading " + repository.save(new Course("Spring Boot intro 2", "Introduction about spring boot init data 2")));
        };
    }
}
