package baka.annecy.scraper.configuration.security;

import static baka.annecy.scraper.configuration.security.SecurityConstants.JWT_CLAIM_PROPERTY_USER;

import java.security.MessageDigest;
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
  
  public static String encodeToSHA512(String data) {
	    try {
	      MessageDigest md = MessageDigest.getInstance("SHA-512");
	      md.update(data.getBytes("UTF-8"));
	      byte[] mdData = md.digest();
	      return convertToHex(mdData);
	    } catch (Exception e) {
	      return null;
	    }
	  }

	  private static String convertToHex(byte[] data) {
	    StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < data.length; i++) {
	      int halfbyte = (data[i] >>> 4) & 0x0F;
	      int two_halfs = 0;
	      do {
	        if ((0 <= halfbyte) && (halfbyte <= 9)) buf.append((char) ('0' + halfbyte));
	        else buf.append((char) ('a' + (halfbyte - 10)));
	        halfbyte = data[i] & 0x0F;
	      } while (two_halfs++ < 1);
	    }
	    return buf.toString();
	  }
}
