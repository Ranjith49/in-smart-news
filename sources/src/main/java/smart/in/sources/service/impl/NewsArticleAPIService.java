package smart.in.sources.service.impl;

import rx.Single;
import smart.in.common.service.NWResponseType;
import smart.in.sources.service.entity.NewsArticleAPIResponse;

/**
 * InterfaceImplementation for news Articles
 *
 * @author ranjithsuda
 */

public interface NewsArticleAPIService {

  void requestArticles();

  void cancelArticles();

  interface CallBack {

    void onNewsArticleObservable(Single<NewsArticleAPIResponse> responseSingle);

    void onNewsArticleErrorObservable(Single<NWResponseType> errorSingle);
  }
}
