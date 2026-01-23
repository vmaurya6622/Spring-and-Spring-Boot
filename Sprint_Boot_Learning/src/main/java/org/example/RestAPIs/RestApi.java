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
    private Integer age; // int cannot be null but Integer which is a wrapper class can be null.
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
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        return removed ? "User deleted" : "User not found";
    }


    @PutMapping({"/{id}"})
    public String updateUser(@PathVariable int id, @RequestBody User userUpdate) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                userUpdate.setId(id);
                users.set(i, userUpdate);
                return "User " + id + " replaced (age is now null)";
            }
        }
        return "User "+id+" updated";
    }

//    @PatchMapping("/{id}")
//    public String patchUser(@PathVariable int id,
//                            @RequestBody User updates) {
//
//        for (User user : users) {
//            if (user.getId() == id) {
//
//                // update only if value is present
//                if (updates.getName() != null) {
//                    user.setName(updates.getName());
//                }
//
//                if (updates.getAge() != null) {
//                    user.setAge(updates.getAge());
//                }
//
//                return "User " + id + " partially updated";
//            }
//        }
//        return "User not found";
//    }

}
@SpringBootApplication
public class RestApi {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestApi.class, args);
//        UserController userController = (UserController) ctx.getBean("userController");
//        userController.getUsers();

    }
}
