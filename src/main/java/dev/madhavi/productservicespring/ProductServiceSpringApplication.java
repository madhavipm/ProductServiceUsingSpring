package dev.madhavi.productservicespring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan(basePackages = {"dev.madhavi.ProductServiceSpring.models", "dev.madhavi.ProductServiceSpring.inheritancetypes"})
@SpringBootApplication
public class ProductServiceSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceSpringApplication.class, args);
    }
}

