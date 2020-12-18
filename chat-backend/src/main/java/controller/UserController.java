package controller;

import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.UserService;

@RestController("UserController")
public class UserController {
    private UserService userService;

    @GetMapping("/user/{name}")
    public Mono<User> findByName(@PathVariable String name){
        return userService.findByName(name);
    }

    @GetMapping("/user/{id}")
    public Mono<User> findById(@PathVariable String id){ return userService.findById(id); }




}
