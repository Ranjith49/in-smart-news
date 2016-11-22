package smart.in.common.helper.pref;

/**
 * Represents each preference that is saved on the app.
 *
 * @author ranjith.suda
 */
public interface SavedPreference {

  PreferenceType getPreferenceType();

  String getName();
}
