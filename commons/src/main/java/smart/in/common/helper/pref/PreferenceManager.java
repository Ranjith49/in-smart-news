package smart.in.common.helper.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import smart.in.common.helper.DataUtil;
import smart.in.common.helper.Utils;


/**
 * Common class for saving data in preference
 * SavedPreference Interface need to be implemented , allows saving different section preferences
 * <p>
 * Int , Long , String and Boolean are supported out of Box ..
 *
 * @author ranjith.suda
 */
public class PreferenceManager {

  /**
   * Method to check whether the given saved pref is present in the devie or not
   *
   * @param savedPreference -- saved preference
   * @return -- whether present or not ..
   */
  public static boolean containsPreference(@NonNull SavedPreference savedPreference) {
    PreferenceType preferenceType = savedPreference.getPreferenceType();
    if (preferenceType == null) {
      return false;
    }

    Context c = Utils.getAppContext();
    String preferenceFileName = preferenceType.getFileName();
    String preferenceKey = savedPreference.getName();
    SharedPreferences sharedPreferences = c.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
    return sharedPreferences.contains(preferenceKey);
  }

  /**
   * Method to save a preference in file with key provided by SavedPreference attribute
   *
   * @param savedPreference -- saved preference
   * @param value           -- value [Converted internally to Int ,Long , String]
   */
  public static void savePreference(@NonNull SavedPreference savedPreference, Object value) {
    String key = savedPreference.getName();
    String fileName = savedPreference.getPreferenceType().getFileName();
    Context context = Utils.getAppContext();

    SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    if (value instanceof String) {
      editor.putString(key, value.toString());
    } else if (value instanceof Integer) {
      Integer intValue = DataUtil.parseInt(value.toString(), -1);
      if (intValue != -1) {
        editor.putInt(key, intValue);
      }
    } else if (value instanceof Long) {
      Long longValue = DataUtil.parseLong(value.toString(), -1L);
      if (longValue != -1L) {
        editor.putLong(key, longValue);
      }
    } else if (value instanceof Boolean) {
      Boolean booleanValue = DataUtil.parseBoolean(value.toString(), null);
      if (booleanValue != null) {
        editor.putBoolean(key, booleanValue);
      }
    }
    editor.apply();
  }

  /**
   * Method to get the Preference value of T from the Saved Preference
   *
   * @param savedPreference -- save Preference
   * @param defaultVal      -- default value of T type
   * @return -- default value in instance of T
   */
  public static <T> T getPreference(@NonNull SavedPreference savedPreference, T defaultVal) {
    String key = savedPreference.getName();
    String fileName = savedPreference.getPreferenceType().getFileName();
    Context context = Utils.getAppContext();

    SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    if (defaultVal instanceof String) {
      return (T) preferences.getString(key, defaultVal.toString());

    } else if (defaultVal instanceof Integer) {
      Integer defIntValue = DataUtil.parseInt(defaultVal.toString(), -1);
      Integer intValue = preferences.getInt(key, defIntValue);
      return (T) intValue;

    } else if (defaultVal instanceof Long) {
      Long defLongValue = DataUtil.parseLong(defaultVal.toString(), -1L);
      Long longValue = preferences.getLong(key, defLongValue);
      return (T) longValue;

    } else if (defaultVal instanceof Boolean) {
      Boolean defBooleanValue = DataUtil.parseBoolean(defaultVal.toString(), null);
      Boolean booleanValue = preferences.getBoolean(key, defBooleanValue);
      return (T) booleanValue;
    }
    return defaultVal;
  }

  /**
   * Method to remove the Saved preference from corresponding file having value
   *
   * @param savedPreference -- savedPreference
   */
  public static void remove(@NonNull SavedPreference savedPreference) {
    String key = savedPreference.getName();
    String fileName = savedPreference.getPreferenceType().getFileName();

    SharedPreferences sharedPreferences = Utils.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.remove(key);
    editor.apply();
  }
}
