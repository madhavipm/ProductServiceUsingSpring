package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long id);
    List<Product> getAllProducts();
    Product updateProduct(long id, Product product);
    Product replaceProduct(long id, Product product);
    void deleteProduct(long id);

}
