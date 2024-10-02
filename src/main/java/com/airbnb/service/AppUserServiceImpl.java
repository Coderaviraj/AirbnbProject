package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl {

    private PasswordEncoder passwordEncoder;

    private AppUserRepository appUserRepository;

    public AppUserServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(AppUser user) {

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(3));
        user.setPassword(hashpw);
        return appUserRepository.save(user);

    }
}
