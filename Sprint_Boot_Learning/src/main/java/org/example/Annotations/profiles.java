package org.example.Annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
    -> it helps to use different databases profiles according to different environments.
    -> helps to enable or disable features/ change logging levels/ separate local vs production configs.
        - Conditional bean loading
        - Environment-based configuration
        - safe development
        - clean architecture.
    -> it can only be used with classes, configuration classes and bean methods but cannot be used in ordinary methods.

*/
interface DataSource{
    String getData();
}

@Component
@Profile("developer")
class Developer implements DataSource{
    public String getData(){
        return "Developer is here";
    }
}
@Profile("engineer")
class engineer implements DataSource{
    public String getData(){
        return "Engineer is here";
    }
}

@SpringBootApplication
public class profiles {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(profiles.class,args);
        DataSource dataSouce = context.getBean(DataSource.class);
        System.out.println("Output is: "+dataSouce.getData());

    }
}
