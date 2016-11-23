package smart.in.common.helper;

import android.content.Intent;

import smart.in.common.CommonConstants;
import smart.in.common.entity.AppSectionType;
import smart.in.common.helper.pref.GenericPreferenceType;
import smart.in.common.helper.pref.PreferenceManager;

/**
 * Common Navigator used for application navigating between the the various Sections
 *
 * @author ranjithsuda
 */
public class CommonNavigator {

  /**
   * Method to be called , when we want to navigate to last know selected section
   * If not found , it defaults to the Sources Section
   */
  public static void gotToLastSectionSelected() {
    String cur_Section = PreferenceManager.getPreference(GenericPreferenceType.APP_SECTION,
        AppSectionType.getStringValue(AppSectionType.SOURCES));
    goToSection(AppSectionType.getSection(cur_Section));
  }

  /**
   * Method to be called , when we want to navigate to a known App Section
   *
   * @param sectionType -- App section type
   */
  public static void goToSection(AppSectionType sectionType) {
    Intent intent = new Intent();
    switch (sectionType) {
      case SOURCES:
        intent.setAction(CommonConstants.SOURCES_ACTION);
        break;
      case INBOX:
        intent.setAction(CommonConstants.INBOX_ACTION);
        break;
      case HEADLINES:
        intent.setAction(CommonConstants.HEADLINES_ACTION);
        break;
      case PROFILE:
        intent.setAction(CommonConstants.PROFILE_ACTION);
        break;
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    Utils.getAppContext().startActivity(intent);
  }
}
