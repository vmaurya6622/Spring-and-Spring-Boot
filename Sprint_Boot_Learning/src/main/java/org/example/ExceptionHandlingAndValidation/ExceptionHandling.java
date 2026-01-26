package org.example.ExceptionHandlingAndValidation;
/*
    -> it ensures that when code throws error , they are caught and handled properly so that code doesn't break
    main tools include @ExceptionHandler for specific exceptions and @ControllerAdvice for global handling of errors.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("students")
class Student{
    private Integer id;
    private String name;
    private Integer age;
    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }
}

@RestController
@RequestMapping("/students")
class StudentController{
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id){
        // if the student is not found with this id then exception will be handled.
        if(id!=1)
            throw new StudentNotFoundException("Student not found with id: "+id); // this will be printed if the student with id other than 1 is checked.
        return new Student(1,"Vishal",22); // i am presuming that the database has only one student.
    }
}

@ControllerAdvice
class GlobalExceptionHandler{
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@SpringBootApplication
public class ExceptionHandling {
    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandling.class, args);
    }
}
