package smart.in.sources.entity;

import java.io.Serializable;

/**
 * Entity class for an Article
 *
 * @author ranjithsuda
 */
public class ArticleEntity implements Serializable {

  String author;
  String title;
  String description;
  String url;
  String urlToImage;
  String publishedAt;

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public String getUrlToImage() {
    return urlToImage;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

}
