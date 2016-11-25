package smart.in.common.helper.config;

/**
 * Application Config that is used for getting the Configuration
 * This config is used across the application modules
 *
 * @author ranjith
 */

public class AppConfig {

  private static AppConfig instance;
  private final AppConfigBuilder appConfigBuilder;

  private AppConfig(AppConfigBuilder appConfigBuilder) {
    this.appConfigBuilder = appConfigBuilder;
  }

  public static AppConfig getInstance() {
    if (instance == null) {
      return null;
    } else {
      return instance;
    }
  }

  public static AppConfig createInstance(AppConfigBuilder appConfigBuilder) {
    if (instance == null) {
      synchronized (AppConfig.class) {
        if (instance == null) {
          instance = new AppConfig(appConfigBuilder);
        }
      }
    }
    return instance;
  }

  public String getAPIKey() {
    return appConfigBuilder.apiKey;
  }

  public String getAppUrl() {
    return appConfigBuilder.apiEndPoint;
  }

  public boolean isLogEnabled() {
    return appConfigBuilder.logEnabled;
  }
}
