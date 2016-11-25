package smart.in.sources.helper;

import android.content.Context;
import android.content.Intent;

import smart.in.sources.entity.SourceEntity;
import smart.in.sources.view.activity.ArticlesActivity;

/**
 * Helper class responsbible for navigating in-side the Sources module
 *
 * @author ranjithsuda
 */

public class SourcesNavigator {

  /**
   * Method to navigate to Article Screen
   *
   * @param context      -- context
   * @param sourceEntity -- sourceEntity to be passed
   */
  public static void goToArticleScreen(Context context, SourceEntity sourceEntity) {
    Intent intent = new Intent(context, ArticlesActivity.class);
    intent.putExtra(SourcesConstants.BUNDLE_SOURCE_ENTITY, sourceEntity);
    context.startActivity(intent);
  }
}
