package br.com.blue.manager.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTCandidateProvider {
  @Value("${security.token.secret.candidate}")
  private String secretKey;

  public String validateToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      return JWT.require(algorithm).build().verify(token).getSubject();

    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return "";
    }

  }
}
