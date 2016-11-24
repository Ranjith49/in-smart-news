package smart.in.sources.entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Entity holding the Source details from the News Sources API response
 *
 * @author ranjith
 */
public class SourceEntity implements Serializable {

  String id;
  String name;
  String description;
  String url;
  String category;
  String language;
  String country;
  SourceLogoEntity urlsToLogos;
  ArrayList<String> sortBysAvailable;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public String getCategory() {
    return category;
  }

  public String getLanguage() {
    return language;
  }

  public String getCountry() {
    return country;
  }

  public SourceLogoEntity getUrlsToLogos() {
    return urlsToLogos;
  }

  public ArrayList<String> getSortBysAvailable() {
    return sortBysAvailable;
  }

  @Override
  public String toString() {
    return "[ "
        + "ID : " + id
        + "Name : " + name
        + "Description : " + description
        + "URL : " + url
        + "Category : " + category
        + "Language : " + language
        + "Country : " + country
        + "Source Logo Entitiy : " + urlsToLogos.toString()
        + "sorts : " + sortBysAvailable
        + " ]";
  }
}
