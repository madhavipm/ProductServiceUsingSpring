package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.repositories.CategoryRepository;
import dev.madhavi.productservicespring.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Getter
@Setter

public class SelfProductService implements ProductService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    //patch
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
    Optional<Product>  optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("product with id " + id + " does not exist");
        }
        Product productInDB = optionalProduct.get();
        if(product.getTitle() != null){
            productInDB.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDB.setPrice(product.getPrice());
        }
        return productRepository.save(productInDB);
    }
    //put
    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            product.setId(id);
           productRepository.save(product);
        }
        Product productInDB = optionalProduct.get();
        if(product.getTitle() != null){
            productInDB.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDB.setPrice(product.getPrice());
        }
        return productRepository.save(productInDB);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
        if(category != null){
            if(category.getId() == null){
                //save the new category
                category = categoryRepository.save(category);
            }
            product.setCategory(category);
        }
        return productRepository.save(product);
    }
}