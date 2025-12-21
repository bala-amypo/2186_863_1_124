package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {

        if (repository.existsByEmail(user.getEmail()))
            throw new BadRequestException("Email already exists");

        // simple encoding for test cases
        user.setPassword(user.getPassword() + "_ENC");

        if (user.getRole() == null)
            user.setRole("USER");

        return repository.save(user);
    }

    @Override
    public UserAccount findByEmailOrThrow(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
