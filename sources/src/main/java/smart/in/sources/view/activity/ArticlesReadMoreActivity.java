package smart.in.sources.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;

import smart.in.common.helper.Logger;
import smart.in.sources.R;
import smart.in.sources.entity.ArticleEntity;
import smart.in.sources.helper.ArticleWebViewClient;
import smart.in.sources.helper.SourcesConstants;

/**
 * Activity for loading the Read more / complete article in a webview
 *
 * @author ranjithsuda
 */
public class ArticlesReadMoreActivity extends Activity {

  private final String TAG = ArticlesReadMoreActivity.class.getSimpleName();
  private ArticleEntity entity;
  private ProgressBar progressBar;
  private WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_articles_read_more);

    getActionBar().setDisplayHomeAsUpEnabled(true);
    initView();
  }

  private void initView() {
    entity = (ArticleEntity) getIntent().getSerializableExtra(SourcesConstants.BUNDLE_ARTICLE_ENTITY);
    if (entity == null) {
      Logger.d(TAG, "Entity is null , nothing we can do");
      finish();
    }
    getActionBar().setTitle(entity.getTitle());
    progressBar = (ProgressBar) findViewById(R.id.article_web_view_load);
    webView = (WebView) findViewById(R.id.article_web_view);
    webView.setWebViewClient(new ArticleWebViewClient(progressBar));
    webView.loadUrl(entity.getUrl());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int i = item.getItemId();
    if (i == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
