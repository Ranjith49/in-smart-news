package smart.in.common.service;

/**
 * NW Service Interface to be implemented by all Service Implemenatations
 *
 * @author ranjith
 */
public interface NWServiceInterface {

  /**
   * Method saying to start the API request
   */
  void requestAPI();

  /**
   * Method saying to cancel the API request
   */
  void cancelAPI();
}
