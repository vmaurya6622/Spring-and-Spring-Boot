package org.example.SpringJPA;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    -> JPA (java persistence API) ek java framework hai jo database ke sath kaam karna easy bana deta hai..
    -> no need to write plain Database queries and we write only java methods to get our work done.
    -> works only for relational databases
    -> Controller → Service → Repository → Database
    ->  save() : Insert and Update
    -> findById():
    -> findAll() : select * from student wali cheez.
    -> deleteById(): delete user where id = 1 wali command;
        Database	Java
        Table	Entity
        Column	Field
        Row	Object
    -> it must not include:
        - Business logic
        - Calculations
        - if-else based rules
        - Validation
    For joins in SQL we must use OneToMany(mappedBy= "student") and ManyToOne
    use @Query("sql query") to give sql queries directly this is JPQL(java persistence query language).


    @OneToMany(mappedBy = "user")
    List<Order> orders;
    this means that one user may multiple orders.

    ORM is object relational mapping.
    -> given table primary key is written inside of the object and ManyToOne join column where we have to define another
        entry from different table ki primary key then it will be foreign key.

*/
@Entity
@Table(name="student")
class Student{
    @Id
    private Integer id;
    private String name;
    private Integer age;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
interface StudentRepo extends JpaRepository<Student,Integer> {
    Student findByName(String name);
    List<Student> findByAge(Integer age);
}
/*
    Now we are going to add a service layer to our code. so in here only the services are provided to the 
    working profile that are gonna work.
    -> here, the working code gives an essence of what are the task that the code is going to do.
 */

@Service
class StudentService{
    private final StudentRepo StudentRepo;

    public StudentService(StudentRepo StudentRepo) {
        this.StudentRepo = StudentRepo;
    }
    public Student SaveStudent(Student student){
        return StudentRepo.save(student);
    }
    
    public Student findByName(String name){
        return this.StudentRepo.findByName(name);
    }
    
    public List<Student> findByAge(Integer age){
        return this.StudentRepo.findByAge(age);
    }
    public List<Student> findAll(){
        return this.StudentRepo.findAll();    
    }
    public void deletebyId(Integer id){
        this.StudentRepo.deleteById(id);
    }
}
/*
    -> this class will help the Student service to get control over the services it will basically define what
       essentially a service will do in the code
 */
@RestController
@RequestMapping("studentApp")
class StudentController{
    private final StudentService studentService;
    public StudentController( StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping//this post mapping is used to add an element of Student in the list
    public Student addStudent(@RequestBody Student student){
        return studentService.SaveStudent(student);
    }

    @GetMapping("/by-name/{name}")
    public Student getStudentByName(@PathVariable String name){
        return this.studentService.findByName(name);
    }
    @GetMapping("/by-age/{age}")
    public List<Student> getStudentByAge(@PathVariable Integer age){
        return this.studentService.findByAge(age);
    }
    @GetMapping
    public List<Student> findAll(){
        return this.studentService.findAll();
    }
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Integer id){
        studentService.deletebyId(id);
        return "Student is deleted successfully with ID: "+id;
    }

}

@SpringBootApplication
public class springJPADemo {
    public static void main(String[] args) {
        SpringApplication.run(springJPADemo.class, args);
    }
}
