package smart.in.sources.service.model;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;
import smart.in.sources.service.entity.NewsArticleAPIResponse;

/**
 * API Interface for Retrofit to get articles of a particular Source
 *
 * @author ranjithsuda
 */

public interface NewsArticlesAPI {


  /**
   * Retrofit Interface to get News Articles Response
   *
   * @param source -- source key
   * @param apiKey -- api key
   * @param sortBy -- how to sort by
   * @return -- API Response
   */
  @GET("articles")
  Single<Response<NewsArticleAPIResponse>> getNewsArticles(@Query("source") String source,
                                                           @Query("apiKey") String apiKey,
                                                           @Query("sortBy") String sortBy);
}
