package smart.in.common.helper.pref;

/**
 * Generic Preference Type used for the application
 *
 * @author ranjithsuda
 */
public enum GenericPreferenceType implements SavedPreference {
  APP_SECTION("appSection");

  String name;

  GenericPreferenceType(String name) {
    this.name = name;
  }

  @Override
  public PreferenceType getPreferenceType() {
    return PreferenceType.GENERIC;
  }

  @Override
  public String getName() {
    return null;
  }
}
