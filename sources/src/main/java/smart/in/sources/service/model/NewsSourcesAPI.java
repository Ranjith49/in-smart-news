package smart.in.sources.service.model;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;
import smart.in.sources.service.entity.NewsSourcesAPIResponse;


/**
 * Interface class holding the Retrofit2 API's for the News Sources
 *
 * @author ranjithsuda
 */
public interface NewsSourcesAPI {

  /**
   * Retrofit Interface for getting the News Sources
   *
   * @param cat     -- category Optional
   * @param lang    -- language Optional
   * @param country -- country Optional
   * @return -- An Observable of Sources of news
   */
  @GET("sources")
  Single<Response<NewsSourcesAPIResponse>> getNewsSources(@Query("category") String cat,
                                                          @Query("language") String lang,
                                                          @Query("country") String country);
}
