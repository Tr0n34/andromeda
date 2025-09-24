package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}