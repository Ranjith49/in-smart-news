package smart.in.sources.service.impl;

import rx.Single;
import smart.in.common.service.NWResponseType;
import smart.in.sources.service.entity.NewsSourcesAPIResponse;

/**
 * News Sources API Service Interface and call backs to the Presenter
 *
 * @author ranjith
 */

public interface NewsSourcesAPIService {

  void requestNewsSources();

  void cancelNewsSources();

  interface CallBack {

    void onNewsSourcesObservable(Single<NewsSourcesAPIResponse> responseSingle);

    void onNewsSourcesErrorObservable(Single<NWResponseType> errorSingle);
  }

}
