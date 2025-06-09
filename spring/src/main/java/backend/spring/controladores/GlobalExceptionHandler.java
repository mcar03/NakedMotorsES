package backend.spring.controladores;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import backend.spring.excepciones.CarroException;


/**
 * Con este controlador, podemos gestionar excepciones "a medida", por ejemplo al hacer transacciones
 * y lanzar el rollback si llegamos a un punto que necesitamos lanzar la excepción, como cuando
 * tenemos en el carrito de la compra más productos de los que hay en stock.
 */

 @ControllerAdvice
 public class GlobalExceptionHandler {
 
     @ExceptionHandler(CarroException.class)
     public ResponseEntity<Map<String, Object>> handleException(CarroException ex) {
         Map<String, Object> errorBody = new HashMap<>();
         errorBody.put("titulo", "Error al procesar su carrito");
         errorBody.put("mensaje", ex.getMessage());
         errorBody.put("timestamp", LocalDateTime.now());
         errorBody.put("status", HttpStatus.BAD_REQUEST.value());
 
         return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
     }
 
     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("status", "error");

        List<FieldError> fieldErrors =  e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(HttpMessageNotReadableException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", "error");
        error.put("message", "JSON mal formado o tipo de dato incorrecto");
        return ResponseEntity.badRequest().body(error);
    }
 }
 