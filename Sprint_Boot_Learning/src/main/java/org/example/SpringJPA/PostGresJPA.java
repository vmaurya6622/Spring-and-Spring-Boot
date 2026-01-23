package org.example.SpringJPA;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(name = "students")
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private String email;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
// getters & setters
}

interface StudentRepository
        extends JpaRepository<Student, Long> {
}

@Configuration
class TestConfig {

    @Bean
    CommandLineRunner test(StudentRepository repo) {
        return args -> {

            Student s = new Student();
            s.setName("Rahul");
            s.setAge(21);
            s.setEmail("rahul@gmail.com");
            s.setCity("Delhi");

            repo.save(s);
            repo.findAll().forEach(System.out::println);
        };
    }
}

@SpringBootApplication
public class PostGresJPA {

    public static void main(String[] args) {
        SpringApplication.run(PostGresJPA.class, args);
    }
}
