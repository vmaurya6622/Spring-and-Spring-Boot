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
@Table(name = "employee")
class Employee {

    @Id
    private Integer id;
    private String name;
    private Integer age;

    // getters & setters
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

/* ================= Repository ================= */

interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findByName(String name);

    List<Employee> findByAge(Integer age);
}

/* ================= Service ================= */

@Service
class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findByName(String name) {
        return employeeRepo.findByName(name);
    }

    public List<Employee> findByAge(Integer age) {
        return employeeRepo.findByAge(age);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public void deleteById(Integer id) {
        employeeRepo.deleteById(id);
    }
}

/* ================= Controller ================= */

@RestController
@RequestMapping("/employeeApp")
class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/by-name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/by-age/{age}")
    public List<Employee> getByAge(@PathVariable Integer age) {
        return employeeService.findByAge(age);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return "Employee deleted successfully with ID: " + id;
    }
}

/* ================= Main ================= */

@SpringBootApplication
public class springJPADemo {

    public static void main(String[] args) {
        SpringApplication.run(springJPADemo.class, args);
    }
}
