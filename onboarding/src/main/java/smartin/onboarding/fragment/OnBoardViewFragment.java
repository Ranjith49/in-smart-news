package smartin.onboarding.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import smartin.onboarding.R;
import smartin.onboarding.helper.OnBoardingConstants;

/**
 * On Boarding Screen Fragment used for drawing the in the View pager
 *
 * @author ranjithsuda
 */

public class OnBoardViewFragment extends Fragment {

  private TextView textView;
  private int fragmentId;

  public static Fragment getInstance(Bundle bundle) {
    OnBoardViewFragment fragment = new OnBoardViewFragment();
    fragment.setArguments(bundle != null ? bundle : new Bundle());
    return fragment;
  }

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    fragmentId = getArguments().getInt(OnBoardingConstants.BUNDLE_ONBOARD_FRAGMENT_ID, 0);
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
    View rootView = inflater.inflate(R.layout.fragment_on_boarding, container, false);
    initView(rootView);
    return rootView;
  }

  private void initView(View root) {
    textView = (TextView) root.findViewById(R.id.onboard_fragment_text);
    textView.setText("Fragment" + String.valueOf(fragmentId));
  }
}
