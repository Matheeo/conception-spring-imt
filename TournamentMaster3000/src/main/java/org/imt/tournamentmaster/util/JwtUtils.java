package org.imt.tournamentmaster.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    private String KEY = "pedro";

    private Algorithm algorithm = Algorithm.HMAC256(KEY);

    public String genereToken(String subject, String[] roles) {
        String token = "";
        try {
            token = JWT.create()
                    .withIssuer("auth0")
                    .withExpiresAt(Instant.now().plusMillis(1000*60*60))
                    .withArrayClaim("roles", roles)
                    .withSubject(subject)
                    .sign(algorithm);
        } catch (Exception e){
            log.error("e: ", e);
        }
        return token;
    }

    public boolean checkToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e){
            log.error("e :", e);
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        DecodedJWT tokenDecoded = JWT.decode(token);
        return tokenDecoded.getSubject();
    }

    public List<String> getRolesFromToken(String token){
        List<String> roles;
        DecodedJWT tokenDecoded = JWT.decode(token);
        roles = tokenDecoded.getClaim("roles").asList(String.class);
        return roles;
    }

}