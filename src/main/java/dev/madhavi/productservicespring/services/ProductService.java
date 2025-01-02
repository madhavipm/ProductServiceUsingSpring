package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product addNewProduct(Product product);

}
