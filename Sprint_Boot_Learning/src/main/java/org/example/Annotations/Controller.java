package org.example.Annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    -> controller is a MVC(Model View Controller) annotation that tells that the specified class will handle web requests.
    -> it receives requests and takes decisions to act upon them.
    -> @Controller is actually a child of @Component it is used to return web pages and handle web request.
    -> use @ResponseBody to return data objects while using @Controller otherwise to return data objects we can directly use @RestController
    -> to return jsp(java server pages) or html we use.
 */

//@org.springframework.stereotype.Controller
//class Homepage {
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "Vishal here!";
//    }
//}
//@org.springframework.web.bind.annotation.RestController

@SpringBootApplication
public class Controller {
    public static void main(String[] args) {
        SpringApplication.run(Controller.class, args);
    }

    @org.springframework.stereotype.Controller
    static class Homepage {
        @GetMapping("/hello")
        @ResponseBody
        public String hello(){
            return "Vishal here!";
        }
    }
}
