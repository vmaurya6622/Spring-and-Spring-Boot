package org.example.Annotations;
/*
    -> @Service is a spring stereotype annotation that tells "ye class service layer ka part hai aur isme business logic likha jayega."
    -> Controller: request handle karta hai
    -> Service: business logic karta.
    -> Repository: database se baat karta hai.
    -> it implement business logics and acts as a bridge between controller and repository.
        @Component	Generic Spring bean
        @Service	Business logic layer, it tells what actually we have to do.
        @Controller	Web layer, it checks the requests.
        @Repository	Data access layer, it tells from where we have to take the data.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// this is a service class
@Service
class CienaServices{
    public String DisplayEmployeeName(){
        return "Rajan";
    }
}

// this is a controller class
@RestController
class ControllerForCienaServices{
    private final CienaServices cienaServices;
    public ControllerForCienaServices(CienaServices cienaServices){
        this.cienaServices = cienaServices;
    }
    @GetMapping("/cienaUser")
//    @ResponseBody   // it will work with/without this
    public String DisplayEmployeeName(){
        return cienaServices.DisplayEmployeeName();
    }
}
@SpringBootApplication
public class serviceDemo {
    public static void main(String[] args) {
        SpringApplication.run(serviceDemo.class, args);
    }
}
