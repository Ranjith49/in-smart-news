package smartin.onboarding.helper;

import android.content.Context;
import android.content.Intent;

import smartin.onboarding.activity.OnBoardingActivity;

/**
 * Class holding the Various required Activity / fragment transaction related to On Boarding Screen
 *
 * @author ranjithsuda
 */
public class OnBoardingNavigator {

  /**
   * Method to launch the On Boarding View
   *
   * @param context -- context of the Screen , from where the call came
   */
  public static void navigateToOnBoardingView(Context context) {
    Intent intent = new Intent(context, OnBoardingActivity.class);
    context.startActivity(intent);
  }
}
