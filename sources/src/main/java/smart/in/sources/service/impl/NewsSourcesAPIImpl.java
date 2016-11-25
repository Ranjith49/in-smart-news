package smart.in.sources.service.impl;

import rx.Single;
import smart.in.common.CommonConstants;
import smart.in.common.helper.Logger;
import smart.in.common.service.NWResponseType;
import smart.in.common.service.NWRestClient;
import smart.in.common.service.NWServiceObservableAdapter;
import smart.in.sources.service.entity.NewsSourcesAPIResponse;
import smart.in.sources.service.model.NewsSourcesAPI;

/**
 * News Sources API Implementation for News Sources API
 *
 * @author ranjith
 */

public class NewsSourcesAPIImpl extends NWServiceObservableAdapter<NewsSourcesAPIResponse>
    implements NewsSourcesAPIService {

  private final String TAG = NewsSourcesAPIImpl.class.getSimpleName();
  private String language = CommonConstants.EMPTY_STRING;
  private String category = CommonConstants.EMPTY_STRING;
  private String country = CommonConstants.EMPTY_STRING;

  private NewsSourcesAPI newsSourcesAPI;
  private CallBack callBack;

  public NewsSourcesAPIImpl(NewsSourcesAPIService.CallBack callBack, String lang, String cat,
                            String country) {
    this.language = lang;
    this.category = cat;
    this.country = country;
    this.callBack = callBack;

    newsSourcesAPI = NWRestClient
        .getResBGtClient()
        .create(NewsSourcesAPI.class);
  }


  @Override
  protected void onNW_Success(Single<NewsSourcesAPIResponse> responseSingle) {
    callBack.onNewsSourcesObservable(responseSingle);
  }

  @Override
  protected void onNW_Error(Single<NWResponseType> errorSingle) {
    callBack.onNewsSourcesErrorObservable(errorSingle);
  }

  @Override
  public void requestNewsSources() {
    //Make sure that prev API is un subscribed..
    if (singleSubscriber != null) {
      singleSubscriber.unsubscribe();
    }

    // Start New Subscription Request here ..
    Logger.d(TAG, "Subscribe -- Sourcxes");
    requestAPI(newsSourcesAPI.getNewsSources(language, category, country));
  }

  @Override
  public void cancelNewsSources() {
    if (singleSubscriber == null) {
      Logger.d(TAG, "There is no one to un subscribe ..");
      return;
    }

    // Cancel the Subscription Request here ..
    Logger.d(TAG, "Un Subscribe -- Sources");
    singleSubscriber.unsubscribe();
  }
}
