package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.Product;
import fr.andromeda.cyb.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByType(ProductType type);

}
