package baka.annecy.scraper.configuration.exception;

@SuppressWarnings("serial")
public class ApplicationAuthorisationException extends ApplicationException {

  public ApplicationAuthorisationException(String messageKey) {
    super(messageKey);
  }
}
