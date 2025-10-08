package fr.andromeda.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "fr.andromeda.auth",
    "fr.andromeda.api"
})
@EntityScan(basePackages = {
        "fr.andromeda.api.entities",
        "fr.andromeda.auth.entities"
})
@EnableJpaRepositories(basePackages = {
        "fr.andromeda.api.repositories",
        "fr.andromeda.auth.repositories"
})
@EnableCaching
public class AuthApplication {

    public static void main(String... args) {
        SpringApplication.run(AuthApplication.class,args);
    }

}
