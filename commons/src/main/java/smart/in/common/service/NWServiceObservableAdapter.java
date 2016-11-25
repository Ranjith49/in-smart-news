package smart.in.common.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Response;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import smart.in.common.helper.Logger;
import smart.in.common.helper.Utils;

/**
 * An Adapter that sits between the N/W Response Observable and Expected Observable by Client
 * Maps the values accordingly and converts to
 * a) Successful , if response is successful
 * b) Error , if response is non-successful / or Error due to N/w Connections
 *
 * @author ranjith
 */
public abstract class NWServiceObservableAdapter<RESPONSE_ENTITY> {

  private static ConnectivityManager manager;
  protected final String TAG = "NW-SERVICE";
  protected SingleSubscriber<Response<RESPONSE_ENTITY>> singleSubscriber;

  /**
   * Method to request actual Network call and convert the response entity to
   * a) Observable<RESPONSE_ENTITY> or
   * b) Single<NWResponseType>
   */
  protected void requestAPI(Single<Response<RESPONSE_ENTITY>> observable) {
    if (null == manager) {
      manager = (ConnectivityManager) Utils.getAppContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    singleSubscriber = new SingleSubscriber<Response<RESPONSE_ENTITY>>() {
      @Override
      public void onError(Throwable e) {
        Logger.d(TAG, e.toString());
        onNW_Error(Single.just(NWResponseType.NW_INTERNAL_ERROR));
      }

      @Override
      public void onSuccess(Response<RESPONSE_ENTITY> o) {
        Logger.d(TAG, o.message());
        processAPIResponse(o);
      }
    };
    if (!isNWConnected(manager)) {
      onNW_Error(Single.just(NWResponseType.NW_NO_INTERNET));
    } else {
      observable
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(singleSubscriber);
    }
  }

  /**
   * Method that returns , whether Network is connected or not
   *
   * @param manager -- connectivity manager instance
   * @return -- return whether network is connected or not
   */
  private boolean isNWConnected(ConnectivityManager manager) {
    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
    return networkInfo != null && networkInfo.isConnected();
  }

  /**
   * Method to process the API response from N/w ..
   *
   * @param response Entity expected from the Retrofit
   */
  private void processAPIResponse(Response<RESPONSE_ENTITY> response) {
    NWResponseType type = NWResponseType.responseType(response.code());
    switch (type) {
      case NW_OK:
        onNW_Success(Single.just(response.body()));
        break;
      case NW_NO_CONTENT:
      case NW_NOT_MODIFIED:
      case NW_UN_AUTHORIZED:
      case NW_NOT_FOUND:
      case NW_INTERNAL_ERROR:
        onNW_Error(Single.just(type));
        break;
      default:
        onNW_Error(Single.just(NWResponseType.NW_INTERNAL_ERROR));
        break;
    }
  }

  /**
   * Call back to the Service impl ,
   * -- Giving the required response entity observable from Retrofit Response Entity
   *
   * @param responseSingle -- Retrofit response entity mapped into  RESPONSE_ENTITY Observable
   */
  protected abstract void onNW_Success(Single<RESPONSE_ENTITY> responseSingle);

  /**
   * Call back to the Service impl ,
   * -- Giving the required Error entity Single Observable from Retrofit Response Entity
   *
   * @param errorSingle -- Retrofit error entity mapped into NWResponseType Observable
   */
  protected abstract void onNW_Error(Single<NWResponseType> errorSingle);
}
