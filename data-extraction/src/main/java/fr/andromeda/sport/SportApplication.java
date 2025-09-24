package fr.andromeda.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SportApplication {

    public static void main(String... args) {
        SpringApplication.run(SportApplication.class,args);
    }

}
