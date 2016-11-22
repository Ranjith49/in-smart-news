package smart.in.common.presenter;

/**
 * Application Base presenter , need to be extended by all the java Presenters in app
 *
 * @author ranjith.suda
 */

public abstract class BasePresenter {

  /**
   * To be called , when the presenter action is to be started
   */
  public abstract void start();

  /**
   * To be called , when the presenter action is to be stopped
   */
  public abstract void stop();
}
