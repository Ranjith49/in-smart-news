package smart.in.sources.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import smart.in.common.service.NWResponseType;
import smart.in.sources.R;
import smart.in.sources.db.SourceMemImpl;
import smart.in.sources.entity.ArticleEntity;
import smart.in.sources.entity.SourceEntity;
import smart.in.sources.entity.SourceUserEntity;
import smart.in.sources.helper.SourcesConstants;
import smart.in.sources.helper.SourcesNavigator;
import smart.in.sources.presenter.NewsArticlePresenter;
import smart.in.sources.view.adapter.ArticleRecyclerViewAdapter;
import smart.in.sources.view.fragment.ArticleSortDialogFragment;
import smart.in.sources.view.view.ArticleRecyclerCallBack;
import smart.in.sources.view.view.NewsArticleView;

public class ArticlesActivity extends Activity implements NewsArticleView, ArticleRecyclerCallBack {

  private final String ARTICLE_SORT_TAG = "Article_Sort_Dialog";
  private RecyclerView articleRecyclerView;
  private LinearLayout articleErrorLayout;
  private ProgressBar articleProgressBar;
  private TextView articleErrorTxt;

  private NewsArticlePresenter presenter;
  private SourceEntity entity;
  private String currentSort;
  private ArticleRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_articles);
    getActionBar().setDisplayHomeAsUpEnabled(true);

    initView();
    startPresenter();
  }

  private void initView() {
    Bundle bundle = getIntent().getExtras();
    entity = (SourceEntity) bundle.getSerializable(SourcesConstants.BUNDLE_SOURCE_ENTITY);
    getActionBar().setTitle(entity.getName());
    currentSort = entity.getSortBysAvailable().get(0); // Considering index Zero

    articleRecyclerView = (RecyclerView) findViewById(R.id.articles_recycler_view);
    articleErrorLayout = (LinearLayout) findViewById(R.id.articles_error_layout);
    articleProgressBar = (ProgressBar) findViewById(R.id.articles_progress_bar);
    articleErrorTxt = (TextView) findViewById(R.id.articles_error_txt);

    //Re-try mechanism ..
    articleErrorLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startPresenter();
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.stop();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_article_view, menu);
    MenuItem item = menu.findItem(R.id.article_sort);
    if (entity.getSortBysAvailable().size() == 1) {
      item.setVisible(false);
    }

    MenuItem item2 = menu.findItem(R.id.article_interest);
    SourceUserEntity userEntity = new SourceUserEntity();
    userEntity.setId(entity.getId());
    userEntity.setName(entity.getName());
    userEntity.setUrlImage(entity.getUrlsToLogos().getSmall());
    if (SourceMemImpl.getInstance().isSourceInProfile(userEntity)) {
      item2.setIcon(R.drawable.heart);
    } else {
      item2.setIcon(R.drawable.heart_outline);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int i = item.getItemId();
    if (i == android.R.id.home) {
      finish();
      return true;
    } else if (i == R.id.article_sort) {
      Bundle bundle = new Bundle();
      bundle.putStringArrayList(SourcesConstants.BUNDLE_ARTICLE_SORT_OPTIONS, entity.getSortBysAvailable());
      ArticleSortDialogFragment fragment = ArticleSortDialogFragment.getInstance(bundle);
      fragment.setCallBack(this);
      fragment.show(getFragmentManager(), ARTICLE_SORT_TAG);
      return true;
    } else if (i == R.id.article_interest) {
      SourceUserEntity userEntity = new SourceUserEntity();
      userEntity.setId(entity.getId());
      userEntity.setName(entity.getName());
      userEntity.setUrlImage(entity.getUrlsToLogos().getSmall());
      userEntity.setAddTime(System.currentTimeMillis());
      boolean added = SourceMemImpl.getInstance().addOrRemoveSourceToProfile(userEntity);
      if (added) {
        item.setIcon(R.drawable.heart);
      } else {
        item.setIcon(R.drawable.heart_outline);
      }
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void showErrorMessage(NWResponseType responseType) {
    switch (responseType) {
      case NW_NO_CONTENT:
        articleErrorTxt.setText(getString(R.string.articles_no_content));
        break;
      case NW_INTERNAL_ERROR:
        articleErrorTxt.setText(getString(R.string.articles_un_expected));
        break;
      case NW_NO_INTERNET:
        articleErrorTxt.setText(getString(R.string.articles_no_internet));
        break;
    }
    articleRecyclerView.setVisibility(View.GONE);
    articleErrorLayout.setVisibility(View.VISIBLE);
  }

  @Override
  public void showSources(ArrayList<ArticleEntity> articles) {
    adapter = new ArticleRecyclerViewAdapter(articles, this);
    articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    articleRecyclerView.setAdapter(adapter);

    articleErrorLayout.setVisibility(View.GONE);
    articleRecyclerView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showProgressMsg(boolean show) {
    if (show) {
      articleProgressBar.setVisibility(View.VISIBLE);
    } else {
      articleProgressBar.setVisibility(View.GONE);
    }
  }

  @Override
  public void onSortOptionClicked(String option) {
    this.currentSort = option;
    presenter.stop();
    articleRecyclerView.setVisibility(View.GONE);
    articleErrorLayout.setVisibility(View.GONE);

    //Stop any current req . and then start again ..
    startPresenter();
  }

  private void startPresenter() {
    presenter = new NewsArticlePresenter(this, entity.getId(), currentSort);
    presenter.start();
  }

  @Override
  public void onItemShare(ArticleEntity entity) {
    Intent intent = new Intent();
    intent.putExtra(Intent.EXTRA_TITLE, entity.getTitle());
    intent.putExtra(Intent.EXTRA_SUBJECT, entity.getTitle());
    intent.putExtra(Intent.EXTRA_TEXT, entity.getUrl());
    intent.setType("text/plain");
    startActivity(intent);
  }

  @Override
  public void onReadMore(ArticleEntity entity) {
    SourcesNavigator.goToArticleWebView(this, entity);
  }
}
