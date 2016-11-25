package smart.in.sources.presenter;

import rx.Single;
import rx.functions.Action1;
import smart.in.common.presenter.BasePresenter;
import smart.in.common.service.NWResponseType;
import smart.in.sources.service.entity.NewsArticleAPIResponse;
import smart.in.sources.service.impl.NewsArticleAPIImpl;
import smart.in.sources.service.impl.NewsArticleAPIService;
import smart.in.sources.view.view.NewsArticleView;

/**
 * Presenter for showing news Articles inside a View
 *
 * @author ranjithsuda
 */
public class NewsArticlePresenter extends BasePresenter implements NewsArticleAPIService.CallBack {

  private NewsArticleAPIService service;
  private NewsArticleView view;

  public NewsArticlePresenter(NewsArticleView view, String sourceKey, String sortBy) {
    this.view = view;
    service = new NewsArticleAPIImpl(this, sourceKey, sortBy);
  }

  @Override
  public void start() {
    view.showProgressMsg(true);
    service.requestArticles();
  }

  @Override
  public void stop() {
    view.showProgressMsg(false);
    service.cancelArticles();
  }

  @Override
  public void onNewsArticleObservable(Single<NewsArticleAPIResponse> responseSingle) {
    view.showProgressMsg(false);
    responseSingle
        .subscribe(new Action1<NewsArticleAPIResponse>() {
          @Override
          public void call(NewsArticleAPIResponse newsArticleAPIResponse) {
            if (newsArticleAPIResponse.getArticles().isEmpty()) {
              view.showErrorMessage(NWResponseType.NW_NO_CONTENT);
            } else {
              view.showSources(newsArticleAPIResponse.getArticles());
            }
          }
        });
  }

  @Override
  public void onNewsArticleErrorObservable(Single<NWResponseType> errorSingle) {
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
