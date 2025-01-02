package dev.madhavi.productservicespring.controlleradvice;

import dev.madhavi.productservicespring.customexceptions.ProductNotFoundException;
import dev.madhavi.productservicespring.dtos.Exceptiondto;
import dev.madhavi.productservicespring.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {
    /*Exceptionhandler is mandatory in controller advice else it will expose our project mvc architecture;
    also for every expection we need to have individual exception handler else it will also expose our project mvc*/
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Exceptiondto> handleArithmeticException(Exception e) {
        Exceptiondto dto = new Exceptiondto();
        dto.setMessage("Arithmetic Exception has happened ");
        dto.setSolution("Please try again");

            ResponseEntity<Exceptiondto> response = new ResponseEntity<Exceptiondto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayOutOfBoundsException(Exception e) {
        ResponseEntity<String> response = new ResponseEntity<>("Something went wrong.Coming from controller Advice1 ",
                HttpStatus.NOT_FOUND);

        return response;
    }@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(Exception e) {
        ResponseEntity<String> response = new ResponseEntity<>("Something went wrong.Coming from controller Advice2 ",
                HttpStatus.FORBIDDEN);

        return response;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    //public ResponseEntity<Exceptiondto>
    // handleProductNotFoundException(Exception e) won't work bcz
    //Exception e is altogether obj for all default exceptions for custom exception we need to create custom obj
    // in exception handler i mention custom exception so i need to send that customexception only
    public ResponseEntity<Exceptiondto> handleProductNotFoundException(ProductNotFoundException ex) {
        Exceptiondto dto = new Exceptiondto();
        dto.setMessage("Product Not Found ");
        dto.setSolution("Please try again with a valid product ");

        ResponseEntity<Exceptiondto> response = new ResponseEntity<Exceptiondto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;

    }

}
