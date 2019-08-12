package baka.annecy.scraper.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ParamType {

  @Getter
  @AllArgsConstructor
  public static enum Type {
    HEADER("header"),
    QUERY("query"),
    PATH("path");

    private String type;
  }

  Type value() default Type.QUERY;
}
