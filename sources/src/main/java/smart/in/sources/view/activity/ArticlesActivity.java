package smart.in.sources.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import smart.in.sources.R;
import smart.in.sources.entity.SourceEntity;
import smart.in.sources.helper.SourcesConstants;

public class ArticlesActivity extends Activity {

  private SourceEntity entity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_articles);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    initView();
  }

  private void initView() {
    Bundle bundle = getIntent().getExtras();
    entity = (SourceEntity) bundle.getSerializable(SourcesConstants.BUNDLE_SOURCE_ENTITY);
    getActionBar().setTitle(entity.getName());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
