package baka.annecy.scraper.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
public class ErrorDto {
  private String message;

  private List<String> errorList;

  private Map<String, String> fieldErrorList;

  public ErrorDto(String message, Map<String, String> fieldErrorList) {
    this.errorList = new ArrayList<>(fieldErrorList.values());
    this.fieldErrorList = fieldErrorList;
    this.message = message;
  }

  public ErrorDto(String message, List<String> errorList) {
    this.errorList = errorList;
    this.message = message;
  }

  public Map<String, String> getFieldErrorList() {
    return fieldErrorList;
  }

  public List<String> getErrorList() {
    return errorList;
  }

  public String getMessage() {
    return message;
  }
}
