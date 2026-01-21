package org.example;


import org.springframework.boot.autoconfigure.SpringBootApplication;

//public class SpringBootLearningApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootLearningApplication.class, args);
//    }
//}
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configurations.class);

        Vehicle vehicle = context.getBean(Vehicle.class);
        vehicle.drive();
    }
}
