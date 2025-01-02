package dev.madhavi.productservicespring.repositories;

import dev.madhavi.productservicespring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // ProductRepo should contain all the methods(CRUD)  that are related to PRODUCT MODEL
    Product findProductById(Long id);
    List<Product> findByPriceGreaterThan(double price);

    //select * from products where price > x

    List<Product> findByPriceLessThan(double price);

    //select * from products where price > x

    List<Product> findByTitleLike(String title);


    List<Product> findByTitleIgnoreCase(String title);
    List<Product> findByTitleContaining(String title);
    List<Product> findTop5ByTitleLike(String title);
    List<Product> findProductByTitleAndPriceGreaterThan(String title, double price);

    List<Product> findByTitleOrderById(String title);

    void deleteById(Long id);
}







/*
1.Repository should be an Interface
2.Repository should extends jpa
3.And follow jpa<Model name of that repository ,Primary key datatype of that model
Spring Data JPA creates the method signature.
Hibernate translates it into SQL.
Hibernate uses JDBC to execute the generated SQL against the database
In a Spring Boot project with Hibernate:

JDBC is used internally by Hibernate for database interactions.
You don’t need to explicitly mention or manage JDBC code unless you have a specific requirement.
Spring Boot simplifies everything, allowing you to focus on high-level JPA code and let Hibernate take care of the rest.


 */