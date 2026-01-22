package org.example.RestAPIs;
/*
    -> Representational state transfer
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

class User{
    private int id;
    private String name;
    private int age;
    public User(){

    }
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@RestController
@RequestMapping("/users")
class UserController {
    private List<User> users = new ArrayList<User>();
    @GetMapping
    public List<User>getUsers() {
        return users;
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        users.add(user);
        return "Added user";
    }
    @PostMapping("/bulk")
    public String createUsers(@RequestBody List<User> list) {
        users.addAll(list);
        for(User user : users){
            System.out.println(user);
        }
        return users.size() + " users created successfully";
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.get(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        users.remove(id);
        return "Deleted fu user";
    }
}
@SpringBootApplication
public class RestApi {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestApi.class, args);
//        UserController userController = (UserController) ctx.getBean("userController");
//        userController.getUsers();

    }
}
