package baka.annecy.scraper.configuration.security;

import static baka.annecy.scraper.configuration.security.SecurityConstants.JWT_CLAIM_PROPERTY_USER;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SecurityUtils {

  @Autowired private ObjectMapper objectMapper;

  @Value("${baka.security.key}")
  private String key;

  public <T> T parseJwToken(String jwtToken, Class<T> clazz) {
    Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);

    Object user = claims.getBody().get(JWT_CLAIM_PROPERTY_USER);
    return objectMapper.convertValue(user, clazz);
  }

  public <T> String generateJwToken(T domainDto) {
    return generateJwToken(domainDto, Instant.now().plus(1, ChronoUnit.DAYS));
  }

  public <T> String generateJwToken(T domainDto, Instant expirationDateTime) {

    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, key)
        .claim(JWT_CLAIM_PROPERTY_USER, domainDto)
        .setExpiration(new Date(expirationDateTime.toEpochMilli()))
        .compact();
  }
}
