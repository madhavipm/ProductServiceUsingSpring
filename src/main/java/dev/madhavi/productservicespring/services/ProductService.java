package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    Page<Product> getAllProducts(int pageNumber, int pageSize);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product addNewProduct(Product product);

}
