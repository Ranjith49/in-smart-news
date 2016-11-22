package in.news.smart;

import android.app.Application;

import smart.in.common.helper.Utils;

/**
 * Android Application Class for doing any initialization only once for the Life time
 *
 * @author ranjithsuda
 */

public class SmartNewsApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Utils.setAppContext(this);
  }
}
