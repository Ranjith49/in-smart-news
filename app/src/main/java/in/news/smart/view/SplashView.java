package in.news.smart.view;

/**
 * Interface Responsible for interacting between Splash Presenter and Splash View
 *
 * @author ranjithsuda
 */
public interface SplashView {

  /**
   * Method to be called when User want to go to On boarding View ..
   */
  void showOnBoardingView();

  /**
   * Method to be called when On boarding is done / subsequent launches for opening the last section
   */
  void goToLastSectionSelected();

}
