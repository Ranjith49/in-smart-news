package smartin.onboarding.helper;

import smart.in.common.helper.pref.PreferenceType;
import smart.in.common.helper.pref.SavedPreference;

/**
 * On Boarding Preferences for the On Boarding Module
 *
 * @author ranjithsuda
 */
public enum OnBoardingPrefType implements SavedPreference {

  ON_BOARD_DONE("appOnBoardDone");
  String name;

  OnBoardingPrefType(String name) {
    this.name = name;
  }

  @Override
  public PreferenceType getPreferenceType() {
    return PreferenceType.ONBOARDING;
  }

  @Override
  public String getName() {
    return name;
  }
}
