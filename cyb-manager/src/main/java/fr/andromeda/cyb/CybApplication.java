package fr.andromeda.cyb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
public class CybApplication {

    public static void main(String... args) {
        SpringApplication.run(CybApplication.class,args);
    }

}
