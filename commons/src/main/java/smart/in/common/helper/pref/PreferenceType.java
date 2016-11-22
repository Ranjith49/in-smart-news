package smart.in.common.helper.pref;

/**
 * Type of preference saved on the app.
 *
 * @author ranjith.suda
 */
public enum PreferenceType {
  GENERIC("appGeneric"),
  ONBOARDING("appOnBoarding"),
  SOURCES("appSources"),
  HEADLINES("appHeadlines"),
  INBOX("appInbox"),
  PROFILE("AppProfile");

  private String fileName;

  PreferenceType(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }
}
