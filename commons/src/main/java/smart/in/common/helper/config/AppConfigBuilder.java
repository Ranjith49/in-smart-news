package smart.in.common.helper.config;

/**
 * Application Configuration Builder to be used to build AppConfig
 * This will be used for building the configuartion from BuildConfig
 *
 * @author ranjith
 */
public class AppConfigBuilder {

  String apiEndPoint;
  boolean logEnabled;

  public AppConfigBuilder() {
    //Nothing to do here ..
  }

  public AppConfigBuilder setLoggerEnabled(boolean logEnabled) {
    this.logEnabled = logEnabled;
    return this;
  }

  public AppConfigBuilder setAPIEndPoint(String endPoint) {
    this.apiEndPoint = endPoint;
    return this;
  }

  public AppConfig createAppConfig() {
    return AppConfig.createInstance(this);
  }
}
