package baka.annecy.scraper;

import static baka.annecy.scraper.configuration.security.SecurityConstants.QUALIFIER_JWT_PAYLOAD_CLASS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import baka.annecy.scraper.configuration.ApplicationProperties;
import baka.annecy.scraper.domain.user.security.UserJwtPayload;

@Configuration
public class AppConfiguration {

  @Bean(QUALIFIER_JWT_PAYLOAD_CLASS)
  public Class<?> jwtPayloadClass() {
    return UserJwtPayload.class;
  }

  @Bean
  public Logger appLogger(ApplicationProperties applicationProperties) {
    return LoggerFactory.getLogger(applicationProperties.getAppName());
  }
}
