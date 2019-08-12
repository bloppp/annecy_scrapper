package baka.annecy.scraper.configuration.exception;

@SuppressWarnings("serial")
public class ApplicationEntityValidationException extends ApplicationException {

  public ApplicationEntityValidationException(String messageKey) {
    super(messageKey);
  }
}
