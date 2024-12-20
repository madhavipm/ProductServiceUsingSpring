package dev.madhavi.productservicespring.controllers;


import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
      ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK);

        return responseEntity;
       /* ResponseEntity<Product> responseEntity = null;
        try{
            Product p = productService.getSingleProduct(id);
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        catch(RuntimeException e){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;*/
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
    /*@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(Exception e) {
        ResponseEntity<String> response = new ResponseEntity<>("Something went wrong.Coming from Product controller",
                HttpStatus.INTERNAL_SERVER_ERROR);

        return response;*/
}
