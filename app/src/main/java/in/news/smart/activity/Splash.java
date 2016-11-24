package in.news.smart.activity;

import android.app.Activity;
import android.os.Bundle;

import in.news.smart.R;
import in.news.smart.presenter.SplashPresenter;
import in.news.smart.view.SplashView;
import smart.in.common.helper.CommonNavigator;
import smartin.onboarding.helper.OnBoardingNavigator;

public class Splash extends Activity implements SplashView {

  private SplashPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    initView();
  }


  @Override
  protected void onResume() {
    super.onResume();
    presenter.start();
  }

  @Override
  protected void onPause() {
    super.onPause();
    presenter.stop();
  }

  /**
   * Method used to initialize the View and its required components.
   */
  private void initView() {
    presenter = new SplashPresenter(this);
    presenter.start();
  }

  @Override
  public void showOnBoardingView() {
    OnBoardingNavigator.navigateToOnBoardingView(this);
    finish();
  }

  @Override
  public void goToLastSectionSelected() {
    CommonNavigator.gotToLastSectionSelected();
    finish();
  }
}
