package baka.annecy.scraper.configuration.exception;

@SuppressWarnings("serial")
public class ApplicationCredentialsException extends ApplicationException {

  public ApplicationCredentialsException(String messageKey) {
    super(messageKey);
  }
}
