package com.preet.bookingrental.Controller;

import com.preet.bookingrental.Dtos.JwtResponse;
import com.preet.bookingrental.Dtos.UserDto;
import com.preet.bookingrental.Dtos.UserLoginDto;
import com.preet.bookingrental.Entities.User;
import com.preet.bookingrental.Repositories.UserRepository;
import com.preet.bookingrental.Services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody UserLoginDto userLoginDto,
            HttpServletResponse response)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPassword()));
        String token = jwtService.generateToken(userLoginDto.getUsername());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/login");
        cookie.setMaxAge(604800);
        cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<User> me()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getPrincipal().toString();

        User user = userRepository.findUserByUsername(username).orElse(null);
        if(user==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleException(BadCredentialsException exception)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }
}
