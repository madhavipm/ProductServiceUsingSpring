package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.models.Product;

import java.util.List;

public interface ProductService {
    public Product getSingleProduct(long id);
    public List<Product> getAllProducts();

}
