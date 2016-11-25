package smart.in.sources.service.impl;

import rx.Single;
import smart.in.common.helper.Logger;
import smart.in.common.helper.config.AppConfig;
import smart.in.common.service.NWResponseType;
import smart.in.common.service.NWRestClient;
import smart.in.common.service.NWServiceObservableAdapter;
import smart.in.sources.service.entity.NewsArticleAPIResponse;
import smart.in.sources.service.model.NewsArticlesAPI;

/**
 * News Article Impl for the News of a particular Source
 *
 * @author ranjithsuda
 */

public class NewsArticleAPIImpl extends NWServiceObservableAdapter<NewsArticleAPIResponse>
    implements NewsArticleAPIService {

  private CallBack callBack;
  private String sourceKey;
  private String sortBy;
  private NewsArticlesAPI newsArticlesAPI;

  public NewsArticleAPIImpl(CallBack callBack, String sourceKey, String sortBy) {
    this.callBack = callBack;
    this.sourceKey = sourceKey;
    this.sortBy = sortBy;

    newsArticlesAPI = NWRestClient
        .getResBGtClient()
        .create(NewsArticlesAPI.class);
  }

  @Override
  protected void onNW_Success(Single<NewsArticleAPIResponse> responseSingle) {
    callBack.onNewsArticleObservable(responseSingle);
  }

  @Override
  protected void onNW_Error(Single<NWResponseType> errorSingle) {
    callBack.onNewsArticleErrorObservable(errorSingle);
  }

  @Override
  public void requestArticles() {
    if (singleSubscriber != null) {
      singleSubscriber.unsubscribe();
    }
    // Start New Subscription Request here ..
    Logger.d(TAG, "Subscribe -- Articles " + sourceKey);
    requestAPI(newsArticlesAPI.getNewsArticles(sourceKey, AppConfig.getInstance().getAPIKey(), sortBy));
  }

  @Override
  public void cancelArticles() {
    if (singleSubscriber == null) {
      Logger.d(TAG, "Subscribe is Null  -- Articles " + sourceKey);
      return;
    }

    singleSubscriber.unsubscribe();
  }
}
