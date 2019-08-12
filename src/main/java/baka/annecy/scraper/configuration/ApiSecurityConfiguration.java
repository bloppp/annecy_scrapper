package baka.annecy.scraper.configuration;

import static baka.annecy.scraper.configuration.security.SecurityConstants.HTTP_HEADER_ACCESS_TOKEN;
import static baka.annecy.scraper.configuration.security.SecurityConstants.HTTP_HEADER_JW_TOKEN;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter
    implements CorsConfigurationSource {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .configurationSource(this)
        .and()
        .headers()
        .defaultsDisabled()
        .cacheControl();
  }

  @Override
  public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addExposedHeader(HTTP_HEADER_ACCESS_TOKEN);
    configuration.addAllowedHeader(HTTP_HEADER_ACCESS_TOKEN);
    configuration.addExposedHeader(HTTP_HEADER_JW_TOKEN);
    configuration.addAllowedHeader(HTTP_HEADER_JW_TOKEN);
    configuration.addExposedHeader(CONTENT_DISPOSITION);
    configuration.addAllowedMethod(HttpMethod.OPTIONS);
    configuration.addAllowedMethod(HttpMethod.DELETE);
    configuration.addAllowedMethod(HttpMethod.PATCH);
    configuration.addAllowedMethod(HttpMethod.POST);
    configuration.addAllowedMethod(HttpMethod.PUT);
    configuration.addAllowedMethod(HttpMethod.GET);
    configuration.addAllowedHeader(CONTENT_TYPE);
    configuration.addAllowedOrigin("*");
    return configuration;
  }
}
