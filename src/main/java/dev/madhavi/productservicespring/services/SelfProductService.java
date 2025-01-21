package dev.madhavi.productservicespring.services;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.models.Category;
import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.repositories.CategoryRepository;
import dev.madhavi.productservicespring.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Getter
@Setter

public class SelfProductService implements ProductService{
    private final PageableHandlerMethodArgumentResolver pageableResolver;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, PageableHandlerMethodArgumentResolver pageableResolver) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.pageableResolver = pageableResolver;
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
    public Page<Product> getAllProducts(int pageNumber ,int pageSize) {
        Page<Product> productPages = productRepository.findAll(
                PageRequest.of(pageNumber, pageSize , Sort.by("price").ascending()));


////        Sort sort = Sort.by("price").ascending().and(Sort.by("title").descending())
////        Sort.by("price").ascending().and(Sort.by("title").ascending().and(Sort.by("quantity").ascending()
        //now we have to convert page of products to list of products or change method signature to page<products>
        return productPages;
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