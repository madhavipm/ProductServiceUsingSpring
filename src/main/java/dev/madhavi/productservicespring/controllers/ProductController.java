package dev.madhavi.productservicespring.controllers;


import dev.madhavi.productservicespring.AuthCommons.AuthenticationCommons;
import dev.madhavi.productservicespring.dtos.UserDto;
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
    private AuthenticationCommons authenticationCommons;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    //Calling fakestoreproductservice to fetch the product with given id
    //For Calling Userservice via product service
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id,
                                                  @RequestHeader("Authorization") String token) {
        try {
            System.out.println("Received token: " + token);

            UserDto user = authenticationCommons.validateToken(token);

            System.out.println("Token is valid for user: " + user.getEmail());

            Product product = productService.getSingleProduct(id);
            return ResponseEntity.ok(product);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 🛑 Unauthorized
        }
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id , @RequestHeader String token) {
        //Inorder to make this servcice authenticated  we can pass token in the
        //input parameter and then we'll have to validate the token from user
        UserDto userDto = authenticationCommons.validateToken(token);
       ResponseEntity<Product> responseEntity = null;
        if(userDto == null){
            return new ResponseEntity<>(null ,HttpStatus.UNAUTHORIZED);
        }

        responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK);

        return responseEntity;

    }*/

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
