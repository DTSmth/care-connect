package com.d424.service;

import com.d424.dao.UserRepository;
import com.d424.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User updateUser(User modifiedUser, String username) {

        User loggedInUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new AccessDeniedException("User not found"));

        if (loggedInUser.getId() != modifiedUser.getId()) {
            throw new AccessDeniedException("Access denied");
        }

        return userRepository.save(modifiedUser);
    }
}
