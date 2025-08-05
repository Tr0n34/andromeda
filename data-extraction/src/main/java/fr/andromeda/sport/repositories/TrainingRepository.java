package fr.andromeda.sport.repositories;

import fr.andromeda.sport.entities.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {


}
