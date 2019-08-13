package baka.annecy.scraper.configuration.api;

import static baka.annecy.scraper.configuration.security.SecurityConstants.HTTP_HEADER_JW_TOKEN;
import static baka.annecy.scraper.configuration.security.SecurityConstants.SECURITY_SCHEME_JW_TOKEN;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.classmate.TypeResolver;

import baka.annecy.scraper.configuration.ApplicationProperties;
import baka.annecy.scraper.configuration.dto.ErrorDto;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApiSwaggerConfiguration {

  @Autowired ApplicationProperties properties;

  @Bean
  public Docket api() {
    TypeResolver typeResolver = new TypeResolver();

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .securitySchemes(securitySchemes())
        .additionalModels(typeResolver.resolve(ErrorDto.class))
        .globalResponseMessage(RequestMethod.GET, globalResponseMessageForRead())
        .globalResponseMessage(RequestMethod.POST, globalResponseMessageForWrite())
        .globalResponseMessage(RequestMethod.PATCH, globalResponseMessageForWrite())
        .globalResponseMessage(RequestMethod.DELETE, globalResponseMessageForWrite());
  }

  private List<? extends SecurityScheme> securitySchemes() {
    return Arrays.asList(new ApiKey(SECURITY_SCHEME_JW_TOKEN, HTTP_HEADER_JW_TOKEN, "header"));
  }

  private List<ResponseMessage> globalResponseMessageForRead() {
    return Arrays.asList(
        new ResponseMessageBuilder()
            .code(500)
            .responseModel(new ModelRef("ErrorDto"))
            .message("Unexpected internal server error")
            .build(),
        new ResponseMessageBuilder()
            .code(401)
            .responseModel(new ModelRef("ErrorDto"))
            .message("Invalid access token or insufficient right")
            .build());
  }

  private List<ResponseMessage> globalResponseMessageForWrite() {

    return Stream.concat(
            globalResponseMessageForRead().stream(),
            Arrays.asList(
                    new ResponseMessageBuilder()
                        .code(400)
                        .responseModel(new ModelRef("ErrorDto"))
                        .message("Invalid request body")
                        .build())
                .stream())
        .collect(Collectors.toList());
  }
}
