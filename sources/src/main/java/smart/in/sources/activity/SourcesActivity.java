package smart.in.sources.activity;

import android.app.Activity;
import android.os.Bundle;

import smart.in.sources.R;
import smart.in.sources.service.impl.NewsSourcesAPIImpl;

/**
 * Home Sources Activity Responsible for the Sources Tab
 */
public class SourcesActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sources);

    NewsSourcesAPIImpl sourcesAPI = new NewsSourcesAPIImpl("en", "general", "in");
  }
}
