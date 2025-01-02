package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Getter
@Setter

public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        //Make a call to product id to fetch with given id
        Optional<Product> optional = productRepository.findById(id);
        //return optional.orElse(null);
        if(optional.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + " not found");
        }
        return optional.get();
    }

    @Override
    public List<Product> getAllProducts() {
         return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
        if(category != null){
            product.setCategory(category);
        }
        return productRepository.save(product);
    }
}