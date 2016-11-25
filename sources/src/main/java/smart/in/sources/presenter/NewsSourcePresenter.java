package smart.in.sources.presenter;

import rx.Single;
import rx.functions.Action1;
import smart.in.common.CommonConstants;
import smart.in.common.presenter.BasePresenter;
import smart.in.common.service.NWResponseType;
import smart.in.sources.service.entity.NewsSourcesAPIResponse;
import smart.in.sources.service.impl.NewsSourcesAPIImpl;
import smart.in.sources.service.impl.NewsSourcesAPIService;
import smart.in.sources.view.view.NewsSourceView;

/**
 * News Source Presenter that gives the UI back with the required data..
 *
 * @author ranjith
 */
public class NewsSourcePresenter extends BasePresenter implements NewsSourcesAPIService.CallBack {

  private NewsSourcesAPIService service;
  private NewsSourceView view;

  public NewsSourcePresenter(NewsSourceView view) {
    this.view = view;
    service = new NewsSourcesAPIImpl(this, CommonConstants.EMPTY_STRING,
        CommonConstants.EMPTY_STRING, CommonConstants.EMPTY_STRING);
  }

  @Override
  public void start() {
    view.showProgressMsg(true);
    service.requestNewsSources();
  }

  @Override
  public void stop() {
    view.showProgressMsg(false);
    service.cancelNewsSources();
  }

  @Override
  public void onNewsSourcesObservable(Single<NewsSourcesAPIResponse> responseSingle) {
    view.showProgressMsg(false);
    responseSingle
        .subscribe(new Action1<NewsSourcesAPIResponse>() {
          @Override
          public void call(NewsSourcesAPIResponse newsSourcesAPIResponse) {
            if (newsSourcesAPIResponse.getSources().isEmpty()) {
              view.showErrorMessage(NWResponseType.NW_NO_CONTENT);
            } else {
              view.showSources(newsSourcesAPIResponse.getSources());
            }
          }
        });
  }

  @Override
  public void onNewsSourcesErrorObservable(Single<NWResponseType> errorSingle) {
    view.showProgressMsg(false);
    errorSingle
        .subscribe(new Action1<NWResponseType>() {
          @Override
          public void call(NWResponseType responseType) {
            view.showErrorMessage(responseType);
          }
        });
  }
}
