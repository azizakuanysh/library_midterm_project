package com.example.library.service;

import com.example.library.dto.UserDto;
import com.example.library.entity.User;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // CREATE
    public User create(UserDto dto) {
        log.info("Creating user");

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }

    // GET BY ID
    public User getById(Long id) {
        log.info("Fetching user with id: {}", id);

        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new ResourceNotFoundException("User not found");
                });
    }

    // GET ALL
    public List<User> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // UPDATE
    public User update(Long id, UserDto dto) {
        log.info("Updating user with id: {}", id);

        User user = getById(id);

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }

    // DELETE
    public void delete(Long id) {
        log.info("Deleting user with id: {}", id);

        User user = getById(id);
        userRepository.delete(user);
    }
}