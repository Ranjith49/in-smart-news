package smart.in.sources.view.view;

import smart.in.sources.entity.ArticleEntity;

/**
 * Call back from Article Recycler to thr Article Display activity
 *
 * @author ranjithsuda
 */

public interface ArticleRecyclerCallBack {

  /**
   * Method invoked when a share item action is clicked
   *
   * @param entity -- entity data
   */
  void onItemShare(ArticleEntity entity);

  /**
   * Method invoked when read more is clicked
   *
   * @param entity -- entitiy data
   */
  void onReadMore(ArticleEntity entity);
}
