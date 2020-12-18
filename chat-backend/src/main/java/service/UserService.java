package service;

import model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public Mono<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Mono<User> findById(String id){return userRepository.findById(id);}
}
