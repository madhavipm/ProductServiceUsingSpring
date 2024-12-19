package dev.madhavi.productservicespring.controllers;


import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //Calling fakestoreproductservice to fetch the product with given id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id){
        return productService.getSingleProduct(id);
    }
    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    public void deleteProduct(){

    }
    //Patch :http:// localhost:9090/products/15
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id ,@RequestBody Product product){
        return productService.updateProduct(id, product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id")  long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

}
