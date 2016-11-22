package smart.in.common.helper;

/**
 * Utils responsible for converting a data object type to other based on assumption and error handling
 *
 * @author ranjithsuda
 */
public class DataUtil {

  /**
   * Method to return the Boolean instance from String , if they can be changed
   *
   * @param stringValue  -- string value
   * @param defaultValue -- default value , if not possible
   */
  public static Boolean parseBoolean(String stringValue, Boolean defaultValue) {
    try {
      return Boolean.parseBoolean(stringValue);
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  /**
   * Method to return the Integer instance from String , if they can be changed
   *
   * @param stringValue  -- string value
   * @param defaultValue -- default value , if not possible
   */
  public static Integer parseInt(String stringValue, Integer defaultValue) {
    try {
      return Integer.parseInt(stringValue);
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  /**
   * Method to return the Long instance from String , if they can be changed
   *
   * @param stringValue  -- string value
   * @param defaultValue -- default value , if not possible
   */
  public static Long parseLong(String stringValue, Long defaultValue) {
    try {
      return Long.parseLong(stringValue);
    } catch (Exception ex) {
      return defaultValue;
    }
  }
}
