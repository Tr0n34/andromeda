package fr.andromeda.api.repositories;

import fr.andromeda.api.entities.errors.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ErrorRepositoryTest {

    @Autowired
    private ErrorRepository errorRepository;

    private Error error1;
    private Error error2;
    private Error error3;

    @BeforeEach
    void setUp() {
        errorRepository.deleteAll(); // Sécurité avant insertion

        error1 = new Error()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setCode("ERR001")
                .setMessage("Bad request")
                .setEntityName("User");

        error2 = new Error()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setCode("ERR002")
                .setMessage("Invalid field")
                .setEntityName("User");

        error3 = new Error()
                .setStatus(HttpStatus.NOT_FOUND)
                .setCode("ERR003")
                .setMessage("Not found")
                .setEntityName("Product");

        errorRepository.save(error1);
        errorRepository.save(error2);
        errorRepository.save(error3);
    }

    @Test
    void testFindByCode() {
        Optional<Error> result = errorRepository.findByCode("ERR002");

        assertThat(result)
                .isPresent()
                .get()
                .matches(err -> err.getMessage().equals("Invalid field"));
    }

    @Test
    void testFindByStatus() {
        Optional<Error> result = errorRepository.findByStatus(HttpStatus.NOT_FOUND);

        assertThat(result)
                .isPresent()
                .get()
                .matches(err -> err.getCode().equals("ERR003"));
    }

    @Test
    void testFindAllByStatus() {
        List<Error> result = errorRepository.findAllByStatus(HttpStatus.BAD_REQUEST);

        assertThat(result)
                .hasSize(2)
                .extracting(Error::getCode)
                .containsExactlyInAnyOrder("ERR001", "ERR002");
    }

    @Test
    void testFindAllByStatusAndEntityName() {
        List<Error> result = errorRepository.findAllByStatusAndEntityName(
                HttpStatus.BAD_REQUEST,
                "User"
        );

        assertThat(result)
                .hasSize(2)
                .extracting(Error::getCode)
                .containsExactlyInAnyOrder("ERR001", "ERR002");
    }

}