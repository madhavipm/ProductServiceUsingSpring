package dev.madhavi.productservicespring.controllers;


import dev.madhavi.productservicespring.models.Product;
import dev.madhavi.productservicespring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    //Calling fakestoreproductservice to fetch the product with given id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK);

        return responseEntity;

    }

    @GetMapping("/")
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    //Patch :http:// localhost:9090/products/15
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }


}
    /*@ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(Exception e) {
        ResponseEntity<String> response = new ResponseEntity<>("Something went wrong.Coming from Product controller",
                HttpStatus.INTERNAL_SERVER_ERROR);

        return response;*/
    /* ResponseEntity<Product> responseEntity = null;
        try{
            Product p = productService.getSingleProduct(id);
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        catch(RuntimeException e){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;*/
