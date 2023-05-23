package com.auth.auth.test;

import com.auth.auth.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class jwtGeneratorTest {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;
    private static final String USERNAME_KEY = "USERNAME";
    @PostConstruct
    public void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(User user){
        return JWT.create()
                .withClaim("USERNAME", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getUsername(String token){
        try {
          if(Jwts.parser().setSigningKey(USERNAME_KEY).parseClaimsJws(token).getBody().getSubject()==null){
              return "Null epta";
          }
            System.out.println("Nulllllelele");
            return "Jwts.parser().setSigningKey(USERNAME_KEY).parseClaimsJws(token).getBody().getSubject()";
        }catch (Exception e){
            return "Bzbz";
        }
    }
}
