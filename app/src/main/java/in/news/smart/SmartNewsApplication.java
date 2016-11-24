package in.news.smart;

import android.app.Application;
import android.content.Context;

import smart.in.common.helper.Utils;
import smart.in.common.helper.config.AppConfigBuilder;


/**
 * Android Application Class for doing any initialization only once for the Life time
 *
 * @author ranjithsuda
 */

public class SmartNewsApplication extends Application {


  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    new AppConfigBuilder()
        .setAPIEndPoint(BuildConfig.API_ENDPOINT)
        .setLoggerEnabled(true)
        .createAppConfig();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Utils.setAppContext(this);
  }
}
