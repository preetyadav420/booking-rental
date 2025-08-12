package com.preet.bookingrental.Controller;

import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user)
    {
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAll()
    {

        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
