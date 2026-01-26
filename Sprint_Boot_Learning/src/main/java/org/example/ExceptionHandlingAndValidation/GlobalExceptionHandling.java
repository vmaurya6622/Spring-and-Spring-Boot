package org.example.ExceptionHandlingAndValidation;
/*
    -> Global exception handler handles the exception for full code
    -> How it works:
            - Pehle specific exception check hoti hai
            - Agar match mila then wahi handler call hota hai.
            - if there is not match then generic Exception.class handler chalta hai aur vahi call hojata hai.
    -> Architecture:
                Controller/ service    -> handles http requests and throws exception if this layer gives exception
                        ↓
                 Exception throw       -> when the service cannot fulfill the request.
                        ↓
                 @ControllerAdvice     -> Global exception handler and intercepts exception thrown by controllers/services and converts into structured HTTP responses
                        ↓
              HTTP response to client  -> Instead of stack trace, client gets clean and structured JSON response for error

*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* ================= Custom Exception ================= */

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

/* ================= Model ================= */

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

/* ================= Controller ================= */

@RestController
@RequestMapping("/soldierz")
class SoldierController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {

        // 1️⃣ Validation first → 400
        if (id <= 0) {
            throw new IllegalArgumentException("Custom exception: ID must be > 0");
        }

        // 2️⃣ Business logic → 404
        if (id != 1) {
            throw new ResourceNotFoundException(
                    "Custom exception: soldier not found with id: " + id
            );
        }

        return new Student(id, "Vishal", 22);
    }
}

/* ================= Global Exception Handler ================= */

@ControllerAdvice
class GlobalExceptionHandlerz {

    // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 500 (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                "Something went incorrect btw this is custom made.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

/* ================= Main ================= */

@SpringBootApplication
public class GlobalExceptionHandling {
    public static void main(String[] args) {
        SpringApplication.run(GlobalExceptionHandling.class, args);
    }
}
