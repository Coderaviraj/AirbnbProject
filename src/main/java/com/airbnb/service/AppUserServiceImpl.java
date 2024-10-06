package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.LoginDto;
import com.airbnb.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl {


    private AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser(AppUser user) {

        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashpw);
        return appUserRepository.save(user);

    }

    public boolean verifyLogin(LoginDto loginDto) {
//        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        Optional<AppUser> opUser = appUserRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername());
        if(opUser.isPresent()) {
            AppUser appUser = opUser.get();
            return BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword());

        }
            return false;
    }
}
