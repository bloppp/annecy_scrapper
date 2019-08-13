package baka.annecy.scraper.configuration.api;

import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import baka.annecy.scraper.configuration.ParamType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;

@Component
public class ApplicationSwaggerParamBuilder implements ExpandedParameterBuilderPlugin {

  @Override
  public boolean supports(DocumentationType type) {
    return DocumentationType.SWAGGER_2 == type;
  }

  @Override
  public void apply(ParameterExpansionContext context) {
    Optional<ParamType> paramType = context.findAnnotation(ParamType.class);
    if (!paramType.isPresent()) {
      return;
    }
    context.getParameterBuilder().parameterType(paramType.get().value().getType());
  }
}
