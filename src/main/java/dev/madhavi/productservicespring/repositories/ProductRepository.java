package dev.madhavi.productservicespring.repositories;
import dev.madhavi.productservicespring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom queries here if needed
}