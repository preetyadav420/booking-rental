package com.preet.bookingrental.Controller;

import com.preet.bookingrental.Dtos.UserDto;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAll()
    {
        List<UserDto> users = new ArrayList<UserDto>();

        userRepository.findAll().forEach(user -> {
            users.add( new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getRole()));
        });
        return ResponseEntity.ok(users);
    }

}
