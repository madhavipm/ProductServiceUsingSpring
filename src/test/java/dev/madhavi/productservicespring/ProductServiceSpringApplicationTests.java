package dev.madhavi.productservicespring;

import dev.madhavi.productservicespring.projections.ProductWithIdAndTitle;
import dev.madhavi.productservicespring.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceSpringApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testDBQueries(){
        ProductWithIdAndTitle productWithIdAndTitle = productRepository.randomHqlMethod(1L);
             System.out.println(productWithIdAndTitle.getId() + " " + productWithIdAndTitle.getTitle());
        }

}

