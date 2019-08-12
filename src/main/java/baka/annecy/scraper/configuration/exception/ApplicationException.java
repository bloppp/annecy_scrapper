package baka.annecy.scraper.configuration.exception;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

  public ApplicationException(String messageKey) {
    super(messageKey);
  }
}
