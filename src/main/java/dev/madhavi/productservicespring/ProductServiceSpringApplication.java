package dev.madhavi.productservicespring;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "dev.madhavi.ProductServiceSpring.models")

@SpringBootApplication
public class ProductServiceSpringApplication {

    public static void main(String[] args) {

    }

}
