package backend.spring.controladores;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 
     // Puedes agregar más handlers de excepción si quieres...
 }
 