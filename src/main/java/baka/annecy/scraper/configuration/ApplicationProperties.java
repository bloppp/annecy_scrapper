package baka.annecy.scraper.configuration;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
@ConfigurationProperties("annecy.scrapper")
public class ApplicationProperties {

  @NotBlank private String appName;

  @NotBlank private String basePackage;

  @NotBlank private String defaultLanguage;
}
