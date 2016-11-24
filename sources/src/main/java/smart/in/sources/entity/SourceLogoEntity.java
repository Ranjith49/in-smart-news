package smart.in.sources.entity;

import java.io.Serializable;

/**
 * Entity holding the Source Logo in the Source Entity Bean
 *
 * @author ranjith
 */

public class SourceLogoEntity implements Serializable {

  String small;
  String medium;
  String large;

  public String getSmall() {
    return small;
  }

  public String getMedium() {
    return medium;
  }

  public String getLarge() {
    return large;
  }
}
