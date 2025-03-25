package mfa.website.multi_factor_auth.controller;

import mfa.website.multi_factor_auth.model.Users;
import mfa.website.multi_factor_auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController // Marks this class as a REST controller for handling API requests
@RequestMapping("/api/auth")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Users user) {
        Optional<Users> existingUser = userRepository.findByUsername(user.getUsername());

        // Check if the username is already taken
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Username already exists!"));
        }

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Assign default role
        userRepository.save(user); // Save the user to the database

        return ResponseEntity.ok(Collections.singletonMap("message", "Registration successful!"));
    }

    // Get user by username
    @GetMapping("/user/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Return 404 if user not found
    }

    // Get user by ID
    @GetMapping("/user/id/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); // Return 404 if user not found
    }
}
