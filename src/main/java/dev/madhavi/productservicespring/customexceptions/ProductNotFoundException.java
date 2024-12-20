package dev.madhavi.productservicespring.customexceptions;

/*public class ProductNotFoundException extends Exception {
is not good practise bcz if we write this we need to use
try catch block in productservice also to avoid using
 try catch block we have to extend runtimeexception */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
