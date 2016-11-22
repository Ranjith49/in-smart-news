package smart.in.common.entity;

/**
 * Various App Section type possible in the application
 *
 * @author ranjith.suda
 */

public enum AppSectionType {
  SOURCES, HEADLINES, INBOX, PROFILE;

  /**
   * Method to get String value of a given App Section
   *
   * @param sectionType -- section type
   * @return -- string value
   */
  public static String getStringValue(AppSectionType sectionType) {
    for (AppSectionType sec : values()) {
      if (sec == sectionType) {
        return sec.toString();
      }
    }
    return SOURCES.toString();
  }


  /**
   * Method to get the App Section type based on the string passed
   *
   * @param value -- string passed
   * @return -- App Section type
   */
  public static AppSectionType getSection(String value) {
    for (AppSectionType appSectionType : values()) {
      if (appSectionType.toString().equalsIgnoreCase(value)) {
        return appSectionType;
      }
    }
    return SOURCES;
  }
}
