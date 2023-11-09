package com.restaurant.restaurantdemoserver.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.restaurant.restaurantdemoserver.data.dto.RestaurantDto;
import com.restaurant.restaurantdemoserver.service.AuthService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    private String secretKey = System.getenv("secret_key");

    private final AuthService authService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();
        DecodedJWT decoded = verifier.verify(token);

        RestaurantDto restaurant = authService.findByLogin(decoded.getIssuer());


        return new UsernamePasswordAuthenticationToken(restaurant, null, Collections.emptyList());
    }
}
