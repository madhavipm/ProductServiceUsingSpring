package dev.madhavi.productservicespring.controllers;


import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
