package smart.in.sources.service.entity;

import java.io.Serializable;
import java.util.ArrayList;

import smart.in.sources.entity.ArticleEntity;

/**
 * News Article API Response
 *
 * @author ranjithsuda
 */

public class NewsArticleAPIResponse implements Serializable {

  String status;
  String source;
  String sortBy;
  ArrayList<ArticleEntity> articles = new ArrayList<>();

  public String getStatus() {
    return status;
  }

  public String getSource() {
    return source;
  }

  public String getSortBy() {
    return sortBy;
  }

  public ArrayList<ArticleEntity> getArticles() {
    return articles;
  }
}
