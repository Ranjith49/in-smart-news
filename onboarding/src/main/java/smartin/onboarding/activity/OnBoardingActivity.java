package smartin.onboarding.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import smart.in.common.helper.CommonNavigator;
import smart.in.common.helper.pref.PreferenceManager;
import smartin.onboarding.R;
import smartin.onboarding.adapter.OnBoardViewPagerAdapter;
import smartin.onboarding.helper.OnBoardingPrefType;

public class OnBoardingActivity extends Activity {


  private ViewPager onBoardViewPager;
  private TextView onBoardSkip;
  private ImageView onBoardLeft;
  private ImageView onBoardRight;

  private int pageIndex;
  private OnBoardViewPagerAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_on_boarding);

    initView();
  }

  /**
   * Method to initialise the View ..
   */
  private void initView() {
    onBoardViewPager = (ViewPager) findViewById(R.id.onboard_view_pager);
    onBoardLeft = (ImageView) findViewById(R.id.onboard_left_image);
    onBoardRight = (ImageView) findViewById(R.id.onboard_right_image);
    onBoardSkip = (TextView) findViewById(R.id.onboard_skip);

    pageIndex = 0;
    adapter = new OnBoardViewPagerAdapter(getFragmentManager());
    onBoardViewPager.setAdapter(adapter);
    onBoardViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        updateBottomBar(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
    updateViewPagerPosition(pageIndex);

    onBoardLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        --pageIndex;
        updateViewPagerPosition(pageIndex);

      }
    });

    onBoardRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        pageIndex++;
        updateViewPagerPosition(pageIndex);
      }
    });

    onBoardSkip.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handleOnBoardingDone();
      }
    });
  }

  /**
   * Method to update the bottom bar of the On Boarding View
   *
   * @param position -- position of the fragment in on boarding view pager
   */
  private void updateBottomBar(int position) {
    switch (position) {
      case 0:
        onBoardLeft.setVisibility(View.GONE);
        onBoardRight.setVisibility(View.VISIBLE);
        onBoardSkip.setText(getResources().getString(R.string.onboard_skip));
        break;
      case 1:
        onBoardLeft.setVisibility(View.VISIBLE);
        onBoardRight.setVisibility(View.VISIBLE);
        onBoardSkip.setText(getResources().getString(R.string.onboard_skip));
        break;
      case 2:
        onBoardLeft.setVisibility(View.VISIBLE);
        onBoardRight.setVisibility(View.GONE);
        onBoardSkip.setText(getResources().getString(R.string.onboard_done));
        break;
    }
  }

  /**
   * Method to update the view pager position
   *
   * @param position -- position
   */
  private void updateViewPagerPosition(int position) {
    onBoardViewPager.setCurrentItem(position);
  }

  /**
   * Method to inform on boarding is done..
   */
  private void handleOnBoardingDone() {
    PreferenceManager.savePreference(OnBoardingPrefType.ON_BOARD_DONE, true);
    CommonNavigator.gotToLastSectionSelected();
    finish();
  }
}
