package smart.in.sources.entity;

/**
 * Entity holding the User selected Sources Infor
 *
 * @author ranjithsuda
 */

public class SourceUserEntity {

  String id;
  String name;
  String urlImage;
  long addTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }

  public long getAddTime() {
    return addTime;
  }

  public void setAddTime(long addTime) {
    this.addTime = addTime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof SourceUserEntity)) {
      return false;
    }

    SourceUserEntity s = (SourceUserEntity) o;
    return s.id.equalsIgnoreCase(id);
  }
}
