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

    public UserAccount register(UserAccount user) {
        if (repository.existsByEmail(user.getEmail()))
            throw new BadRequestException("Email already exists");

        return repository.save(user);
    }

    public UserAccount findByEmailOrThrow(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
