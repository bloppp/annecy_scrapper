package baka.annecy.scraper.configuration.security;

import static baka.annecy.scraper.configuration.MessageConstants.SERVICE_ERROR_JW_TOKEN_NOT_DEFINED;
import static baka.annecy.scraper.configuration.MessageConstants.SERVICE_ERROR_JW_TOKEN_UNPARSABLE;
import static baka.annecy.scraper.configuration.security.SecurityConstants.ATTRIBUTE_JWT_USER;
import static baka.annecy.scraper.configuration.security.SecurityConstants.HTTP_HEADER_JW_TOKEN;
import static baka.annecy.scraper.configuration.security.SecurityConstants.SECURITY_SCHEME_JW_TOKEN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import baka.annecy.scraper.configuration.exception.ApplicationCredentialsException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

  private Class<?> jwtPayloadClass;

  private SecurityUtils securityUtils;

  public SecurityHandlerInterceptor(SecurityUtils securityUtils, Class<?> jwtPayloadClass) {
    this.securityUtils = securityUtils;
    this.jwtPayloadClass = jwtPayloadClass;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = ((HandlerMethod) handler);
      ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
      if (apiOperation == null) {
        return true;
      }

      boolean needJWTToken = false;
      for (Authorization authorization : apiOperation.authorizations()) {
        needJWTToken |= authorization.value().equalsIgnoreCase(SECURITY_SCHEME_JW_TOKEN);
      }

      Object authUser = null;
      String jwtToken = request.getHeader(HTTP_HEADER_JW_TOKEN);
      if (StringUtils.isNotBlank(jwtToken)) {
        try {
          authUser = securityUtils.parseJwToken(jwtToken, jwtPayloadClass);
        } catch (Exception e) {
          throw new ApplicationCredentialsException(SERVICE_ERROR_JW_TOKEN_UNPARSABLE);
        }
      } else if (needJWTToken) {
        throw new ApplicationCredentialsException(SERVICE_ERROR_JW_TOKEN_NOT_DEFINED);
      }

      request.setAttribute(ATTRIBUTE_JWT_USER, authUser);
      return true;
    } else {
      return true;
    }
  }
}
