package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private long expiryTime;

    private Algorithm algorithm;

    @PostConstruct
    public void PostContruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(AppUser user) {
        return JWT.create().
                withClaim("username",user.getUsername())
               .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime)).
                withIssuer(issuer).
                sign(algorithm);
    }
}
