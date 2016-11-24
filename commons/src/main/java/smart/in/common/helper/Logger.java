package smart.in.common.helper;

import android.util.Log;

import smart.in.common.CommonConstants;
import smart.in.common.helper.config.AppConfig;

/**
 * Utility class responsible for Logging the Logs
 *
 * @author ranjith
 */
public class Logger {

  private static final boolean loggerEnabled = AppConfig.getInstance().isLogEnabled();

  public static void d(String aTag, String aMessage) {
    if (loggerEnabled) {
      Log.d(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage);
    }
  }

  public static void d(String aTag, String aMessage, Throwable e) {
    if (loggerEnabled) {
      Log.d(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage, e);
    }
  }


  public static void w(String aTag, String aMessage) {
    if (loggerEnabled) {
      Log.w(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage);
    }
  }

  public static void e(String aTag, String aMessage) {
    if (loggerEnabled) {
      Log.e(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage);
    }
  }

  public static void i(String aTag, String aMessage) {
    if (loggerEnabled) {
      Log.i(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage);
    }
  }

  public static void e(String aTag, String aMessage, Exception e) {
    if (loggerEnabled) {
      Log.e(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage, e);
    }
  }

  public static void w(String aTag, String aMessage, Exception e) {
    if (loggerEnabled) {
      Log.w(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage, e);
    }
  }

  public static void v(String aTag, String aMessage) {
    if (loggerEnabled) {
      Log.v(aTag, aMessage == null ? CommonConstants.EMPTY_STRING + null : aMessage);
    }
  }

  public static void caughtException(Exception e) {
    if (!loggerEnabled || null == e) {
      return;
    }

    if (e.getMessage() != null) {
      Log.e("Caught Exception", e.getMessage());
    } else {
      Log.e("Caught Exception", e.toString());
    }
  }
}

