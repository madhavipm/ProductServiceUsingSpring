package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class FakeStoreProductService implements ProductService{
    @Override
    public Product getSingleProduct(long productId){
        return new Product();
    }
    public List<Product> getAllProducts(){
        return null;
    }
}
