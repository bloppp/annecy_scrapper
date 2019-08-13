package baka.annecy.scraper.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConstants {

  public static final String PENDING_ACTION_ERROR_CANT_SAVE = "pending-action.error.cant.save";

  public static final String VALIDATION_ERROR_TYPE_MISMATCH = "validation.error.type.mismatch";
  public static final String VALIDATION_ERROR_TYPE = "validation.error.type";
  public static final String VALIDATION_ERROR = "validation.error";

  public static final String SERVICE_ERROR_JW_TOKEN_UNPARSABLE =
      "service.error.jw-token.unparsable";
  public static final String SERVICE_ERROR_JW_TOKEN_NOT_DEFINED =
      "service.error.jw-token.not-defined";
  public static final String SERVICE_ERROR_NOT_AUTHORIZED = "service.error.not-authorized";
  public static final String SERVICE_ERROR_UNKOWN = "service.error.unknown";

  public static final String SERVICE_ERROR = "service.error";
  
  public static final String HEADER_JWT = "JW-Token";
}
