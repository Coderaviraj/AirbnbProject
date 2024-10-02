package com.airbnb.controller;


import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.AppUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AppUserRepository appUserRepository;
    private AppUserServiceImpl appUserService;

    public AuthController(AppUserRepository appUserRepository, AppUserServiceImpl appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
        if (opEmail.isPresent()) {
            throw  new UserExists("Email already exists");

        }
        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getUsername());
        if (opUsername.isPresent()) {
            throw  new UserExists("Username already exists");

        }

        AppUser savedUser = appUserService.createUser(user);

        return new ResponseEntity<>(savedUser , HttpStatus.CREATED);

    }
}
