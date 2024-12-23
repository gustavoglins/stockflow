package com.stockflow.domain.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.stockflow.domain.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        logger.info("Starting token generation for user: {}", user.getLogin());
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("StockFlowAPI")
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);
            logger.info("Token generated successfully for user: {}", user.getLogin());
            return token;

        } catch (JWTCreationException exception) {
            logger.error("Error while generating token for user: {}. Error: {}", user.getLogin(), exception.getMessage());
            throw new RuntimeException("Error while generating token: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        logger.info("Starting token validation.");
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String subject = JWT.require(algorithm)
                    .withIssuer("StockFlowAPI")
                    .build()
                    .verify(token)
                    .getSubject();
            logger.info("Token validated successfully for subject: {}", subject);
            return subject;

        } catch (JWTVerificationException exception) {
            logger.warn("Token validation failed. Error: {}", exception.getMessage());
            return "";
        }
    }

    private Instant genExpirationDate() {
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        logger.info("Generated token expiration date: {}", expiration);
        return expiration;
    }
}
