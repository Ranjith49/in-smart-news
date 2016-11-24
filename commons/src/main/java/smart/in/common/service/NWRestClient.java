package smart.in.common.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import smart.in.common.CommonConstants;
import smart.in.common.helper.config.AppConfig;

/**
 * Rest Client Required for the Network Operations
 *
 * @author ranjith
 */

public class NWRestClient {

  /**
   * Method to get the Rest Client for the Application
   *
   * @return -- Retrofit Instance
   */
  private static Retrofit.Builder getRestBuilder() {

    OkHttpClient.Builder okHttp_client = new OkHttpClient.Builder()
        .connectTimeout(CommonConstants.HTTP_CLIENT_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(CommonConstants.HTTP_CLIENT_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(CommonConstants.HTTP_CLIENT_TIMEOUT, TimeUnit.MILLISECONDS);

    if (AppConfig.getInstance().isLogEnabled()) {
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      okHttp_client.addInterceptor(loggingInterceptor);
    }

    return new Retrofit.Builder()
        .baseUrl(AppConfig.getInstance().getAppUrl())
        .client(okHttp_client.build())
        .addConverterFactory(GsonConverterFactory.create());
  }

  /**
   * Method returning the Rest Client with Rx , which works on normal Calling thread
   *
   * @return -- Retrofit Rest client
   */
  public static Retrofit getRestClient() {
    return getRestBuilder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  /**
   * Method returning the Rest-BG Client with RX which works on the Scheduler.io()
   *
   * @return -- Retrofit Rest Client
   */
  public static Retrofit getResBGtClient() {
    return getRestBuilder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }
}
