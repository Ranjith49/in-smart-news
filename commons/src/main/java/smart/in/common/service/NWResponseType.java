package smart.in.common.service;

import java.net.HttpURLConnection;

/**
 * Various Network Types that are possible when an Network operation is done
 *
 * @author ranjith.suda
 */

public enum NWResponseType {
  NW_OK(HttpURLConnection.HTTP_OK),
  NW_INTERNAL_ERROR(HttpURLConnection.HTTP_INTERNAL_ERROR),
  NW_NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND),
  NW_NOT_MODIFIED(HttpURLConnection.HTTP_NOT_MODIFIED),
  NW_NO_CONTENT(HttpURLConnection.HTTP_NO_CONTENT),
  NW_UN_AUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED);

  int code;

  NWResponseType(int code) {
    this.code = code;
  }

  /**
   * Method to return the error code based on responseType
   *
   * @param errorType -- NetworkType
   * @return -- error Code
   */
  public static int responseCode(NWResponseType errorType) {
    for (NWResponseType type : values()) {
      if (type == errorType) {
        return type.code;
      }
    }
    return NW_INTERNAL_ERROR.code;
  }

  /**
   * Method to return the Error Type  based on error code
   *
   * @param code -- error code
   * @return -- NWResponseType
   */
  public static NWResponseType responseType(int code) {
    for (NWResponseType type : values()) {
      if (type.code == code) {
        return type;
      }
    }
    return NW_INTERNAL_ERROR;
  }

}
