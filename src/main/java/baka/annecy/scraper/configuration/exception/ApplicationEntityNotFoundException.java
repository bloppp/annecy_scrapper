package baka.annecy.scraper.configuration.exception;

@SuppressWarnings("serial")
public class ApplicationEntityNotFoundException extends ApplicationException {

  public ApplicationEntityNotFoundException(String messageKey) {
    super(messageKey);
  }
}
