package baka.annecy.scraper.configuration.api;

import static baka.annecy.scraper.configuration.security.SecurityConstants.QUALIFIER_JWT_PAYLOAD_CLASS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import baka.annecy.scraper.configuration.security.SecurityHandlerInterceptor;
import baka.annecy.scraper.configuration.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ApiConfiguration implements WebMvcConfigurer {

  @Autowired
  @Qualifier(QUALIFIER_JWT_PAYLOAD_CLASS)
  private Class<?> jwtPayloadClass;

  @Autowired private SecurityUtils securityUtils;

  @Bean
  public LocaleResolver localeResolver() {
    return new SessionLocaleResolver();
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
    changeInterceptor.setParamName("lang");
    return changeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    try {
      registry.addInterceptor(new SecurityHandlerInterceptor(securityUtils, jwtPayloadClass));
    } catch (Exception e) {
      log.error("Exception", e);
    }
    registry.addInterceptor(localeChangeInterceptor());
  }
}
