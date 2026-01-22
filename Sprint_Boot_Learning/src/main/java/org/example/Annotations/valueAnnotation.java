package org.example.Annotations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/*
    @value spring ko bolta hai ki sun is variable ki value bahar se laa aur inject kar.
    Spring jab bean banata hai toh vo properties file padhta hai aur ${key} ko resolve karta hai.
    aur phir us variable me daal deta hai.
    ye bolra hai value to spring: Mera object tu bana, aur meri values bahar se laa ke daal.
 */
@Component
class student {
    @Value("${student.rollno}")
    private int rollno;

    @Value("${student.name}")
    private String name;

    @Value("${student.age}")
    private int age;
    public void display() {
        System.out.println("Student rollno: " + rollno);
        System.out.println("Student name: " + name);
        System.out.println("Student age: " + age);
    }
}
@SpringBootApplication
public class valueAnnotation{
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(valueAnnotation.class, args);
        student stu = context.getBean(student.class);
        stu.display();
    }
}