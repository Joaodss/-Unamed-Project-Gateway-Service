package com.ironhack.gatewayservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;

import static java.time.Instant.now;

@Component
@Slf4j
public class JwtUtil {
    public static final String SECRET = "secretPass";


    public boolean isInvalid(String authHeader) {
        log.info("Validating token...");
        return authHeader.length() <= "Bearer ".length() || isTokenExpired(authHeader);
    }

    public String getUsernameFromToken(String authHeader) {
        return getDecodedToken(authHeader)
                .getSubject();
    }

    public String[] getRolesFromToken(String authHeader) {
        return getDecodedToken(authHeader)
                .getClaim("roles")
                .asArray(String.class);
    }


    private boolean isTokenExpired(String authHeader) {
        return getDecodedToken(authHeader)
                .getExpiresAt()
                .before(Date.from(now()));
    }

    private DecodedJWT getDecodedToken(String authHeader) {
        var token = authHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());

        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

}
