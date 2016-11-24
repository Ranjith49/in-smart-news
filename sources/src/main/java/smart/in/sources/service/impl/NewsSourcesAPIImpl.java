package smart.in.sources.service.impl;

import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action1;
import smart.in.common.CommonConstants;
import smart.in.common.helper.Logger;
import smart.in.common.service.NWResponseType;
import smart.in.common.service.NWRestClient;
import smart.in.common.service.NWServiceInterface;
import smart.in.common.service.NWServiceObservableAdapter;
import smart.in.sources.service.entity.NewsSourcesAPIResponse;
import smart.in.sources.service.model.NewsSourcesAPI;

/**
 * News Sources API Implementation for News Sources API
 *
 * @author ranjith
 */

public class NewsSourcesAPIImpl extends NWServiceObservableAdapter<NewsSourcesAPIResponse>
    implements NWServiceInterface {

  private final String TAG = NewsSourcesAPIImpl.class.getSimpleName();
  private String language = CommonConstants.EMPTY_STRING;
  private String category = CommonConstants.EMPTY_STRING;
  private String country = CommonConstants.EMPTY_STRING;
  private NewsSourcesAPI newsSourcesAPI;
  private SingleSubscriber singleSubscriber;

  public NewsSourcesAPIImpl(String lang, String cat, String country) {
    this.language = lang;
    this.category = cat;
    this.country = country;
    newsSourcesAPI = NWRestClient
        .getResBGtClient()
        .create(NewsSourcesAPI.class);
  }


  @Override
  protected void onNW_Success(Single<NewsSourcesAPIResponse> responseSingle) {
    // TODO [Remove after debugging , need to be done by Presenter]
    responseSingle
        .subscribe(new Action1<NewsSourcesAPIResponse>() {
          @Override
          public void call(NewsSourcesAPIResponse newsSourcesAPIResponse) {
            Logger.d(TAG, "Response  : " + newsSourcesAPIResponse.toString());
          }
        });
  }

  @Override
  protected void onNW_Error(Single<NWResponseType> errorSingle) {
    // TODO [Remove after debugging , need to be done by Presenter]
    errorSingle.subscribe(new Action1<NWResponseType>() {
      @Override
      public void call(NWResponseType nwResponseType) {
        Logger.d(TAG, "Error Response : " + nwResponseType.toString());
      }
    });
  }

  @Override
  public void requestAPI() {
    //Make sure that prev API is un subscribed..
    if (singleSubscriber != null) {
      singleSubscriber.unsubscribe();
    }

    // Start New Subscription Request here ..
    singleSubscriber = requestAPI(newsSourcesAPI.getNewsSources(language, category, country));
  }

  @Override
  public void cancelAPI() {
    if (singleSubscriber == null) {
      Logger.d(TAG, "There is no one to un subscribe ..");
      return;
    }

    // Cancel the Subscription Request here ..
    singleSubscriber.unsubscribe();
  }
}
