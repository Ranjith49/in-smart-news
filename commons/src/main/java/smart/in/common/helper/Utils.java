package smart.in.common.helper;

import android.app.Application;

/**
 * Application Common Utils are places here
 *
 * @author ranjithsuda
 */
public class Utils {

  private static Application appContext;

  public static Application getAppContext() {
    return appContext;
  }

  public static void setAppContext(Application application) {
    appContext = application;
  }
}
