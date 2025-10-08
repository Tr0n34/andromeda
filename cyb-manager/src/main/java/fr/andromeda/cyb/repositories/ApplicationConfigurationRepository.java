package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.application.ApplicationParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationConfigurationRepository extends JpaRepository<ApplicationParameter, Long>  {

    Optional<ApplicationParameter> findApplicationConfigurationByKey(String key);

}
