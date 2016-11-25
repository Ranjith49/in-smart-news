package smart.in.sources.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import smart.in.common.service.NWResponseType;
import smart.in.sources.R;
import smart.in.sources.entity.SourceEntity;
import smart.in.sources.helper.SourcesNavigator;
import smart.in.sources.presenter.NewsSourcePresenter;
import smart.in.sources.view.adapter.SourcesRecyclerViewAdapter;
import smart.in.sources.view.view.NewsSourceView;
import smart.in.sources.view.view.SourceRecyclerCallBack;

/**
 * Home Sources Activity Responsible for the Sources Tab
 *
 * @author ranjith
 */
public class SourcesActivity extends Activity implements NewsSourceView, SourceRecyclerCallBack {

  private RecyclerView sourcesList;
  private ProgressBar sourcesProgressBar;
  private LinearLayout sourcesErrorLayout;
  private TextView sourcesErrorTxt;

  private NewsSourcePresenter presenter;
  private SourcesRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sources);

    initView();
    presenter = new NewsSourcePresenter(this);
    presenter.start();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.stop();
  }

  private void initView() {
    sourcesErrorTxt = (TextView) findViewById(R.id.sources_error_txt);
    sourcesList = (RecyclerView) findViewById(R.id.sources_recycler_view);
    sourcesErrorLayout = (LinearLayout) findViewById(R.id.sources_error_layout);
    sourcesProgressBar = (ProgressBar) findViewById(R.id.sources_progress_bar);

    //Re-try mechanism ..
    sourcesErrorLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.start();
      }
    });
  }

  @Override
  public void showErrorMessage(NWResponseType responseType) {
    switch (responseType) {
      case NW_NO_CONTENT:
        sourcesErrorTxt.setText(getString(R.string.sources_no_content));
        break;
      case NW_INTERNAL_ERROR:
        sourcesErrorTxt.setText(getString(R.string.sources_un_expected));
        break;
      case NW_NO_INTERNET:
        sourcesErrorTxt.setText(getString(R.string.sources_no_internet));
        break;
    }
    sourcesList.setVisibility(View.GONE);
    sourcesErrorLayout.setVisibility(View.VISIBLE);
  }

  @Override
  public void showSources(ArrayList<SourceEntity> sources) {
    adapter = new SourcesRecyclerViewAdapter(sources, this);
    sourcesList.setLayoutManager(new LinearLayoutManager(this));
    sourcesList.setAdapter(adapter);

    sourcesErrorLayout.setVisibility(View.GONE);
    sourcesList.setVisibility(View.VISIBLE);
  }

  @Override
  public void showProgressMsg(boolean show) {
    if (show) {
      sourcesProgressBar.setVisibility(View.VISIBLE);
    } else {
      sourcesProgressBar.setVisibility(View.GONE);
    }
  }

  @Override
  public void onSourceClicked(SourceEntity entity) {
    SourcesNavigator.goToArticleScreen(this, entity);
  }
}
