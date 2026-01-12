package com.d424.controller;

import com.d424.dao.UserRepository;
import com.d424.model.User;
import com.d424.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

/**
 * The UserController is a class for handling HTTP Requests related to getting User information.
 *
 * It depends on an instance of a UserDAO for retrieving and storing data. This is provided
 * through dependency injection.
 *
 * Note: This class does not handle authentication (registration/login) of Users. That is
 * handled separately in the AuthenticationController.
 */
@RestController
@Validated
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping( path = "/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(); // returns List<User>
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable int userId, Principal principal) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PutMapping
    public User updateProfile(@RequestBody User modifiedUser, Principal principal) {
        // UserService still handles permission checks
        return userService.updateUser(modifiedUser, principal.getName());
    }
}
