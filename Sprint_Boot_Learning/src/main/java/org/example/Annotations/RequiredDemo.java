package org.example.Annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    -> it's a method level annotation which is applied to only setter methods and tells spring that a particular property
       to be injected with a particular property.
    -> it is deprecated after spring 5 and therefore we must use Autowired with required=true to ensure required dependencies are injected.
*/
@Service
class UserService {

    public String getName() {
        return "Vishal";
    }
}
@RestController
class UserController {

    @Autowired()
    private UserService userService;

    @GetMapping("/user")
    public String getUser() {
        return userService.getName();
    }
}
@SpringBootApplication
public class RequiredDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RequiredDemo.class, args);
    }
}
