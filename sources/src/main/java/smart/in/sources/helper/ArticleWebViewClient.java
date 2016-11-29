package smart.in.sources.helper;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebView client , for the Articles Web View
 *
 * @author ranjithsuda
 */

public class ArticleWebViewClient extends WebViewClient {

  private View progressView;

  public ArticleWebViewClient(View progressView) {
    this.progressView = progressView;
  }

  @Override
  public void onPageStarted(WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
    progressView.setVisibility(View.VISIBLE);
  }

  @Override
  public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    progressView.setVisibility(View.GONE);
  }

  @Override
  public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
    super.onReceivedError(view, request, error);
    progressView.setVisibility(View.GONE);
  }
}
