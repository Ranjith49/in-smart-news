package smartin.onboarding.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import smartin.onboarding.fragment.OnBoardViewFragment;
import smartin.onboarding.helper.OnBoardingConstants;

/**
 * Pager Adapter Responsible for sliding the On Boarding Screen
 *
 * @author ranjithsuda
 */

public class OnBoardViewPagerAdapter extends FragmentStatePagerAdapter {

  private final int ITEMS_IN_VIEW_PAGER = 3;

  public OnBoardViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public int getCount() {
    return ITEMS_IN_VIEW_PAGER;
  }

  @Override
  public Fragment getItem(int position) {
    Bundle bundle = new Bundle();
    bundle.putInt(OnBoardingConstants.BUNDLE_ONBOARD_FRAGMENT_ID, position);
    return OnBoardViewFragment.getInstance(bundle);
  }
}
