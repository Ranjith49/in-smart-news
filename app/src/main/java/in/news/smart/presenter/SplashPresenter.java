package in.news.smart.presenter;


import android.os.Handler;
import android.os.Message;

import in.news.smart.view.SplashView;
import smart.in.common.helper.pref.PreferenceManager;
import smart.in.common.presenter.BasePresenter;
import smartin.onboarding.helper.OnBoardingPrefType;

/**
 * Splash Screen Presenter responsible for doing the job of the Splash Screen
 *
 * @author ranjith.suda
 */

public class SplashPresenter extends BasePresenter {

  private final int TIME_SPLASH_DISPLAY = 2000;
  private final int TIME_SPLASH_MSG_ID = 10;
  private SplashView splashView;
  private final Handler splashHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      decideOnBoardVsLastSection();
    }
  };

  public SplashPresenter(SplashView splashView) {
    this.splashView = splashView;
  }

  @Override
  public void start() {
    splashHandler.removeMessages(TIME_SPLASH_MSG_ID);
    splashHandler.sendEmptyMessageDelayed(TIME_SPLASH_MSG_ID, TIME_SPLASH_DISPLAY);
  }

  @Override
  public void stop() {
    splashHandler.removeMessages(TIME_SPLASH_MSG_ID);
  }

  /**
   * Function responsible for deciding whether we want to go to
   * a) On board vs
   * b) Last Visited Section
   */
  private void decideOnBoardVsLastSection() {
    boolean onBoardDone = PreferenceManager.getPreference(OnBoardingPrefType.ON_BOARD_DONE, false);
    if (onBoardDone) {
      splashView.goToLastSectionSelected();
    } else {
      splashView.showOnBoardingView();
    }
  }
}
