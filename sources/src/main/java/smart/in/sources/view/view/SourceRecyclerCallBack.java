package smart.in.sources.view.view;

import smart.in.sources.entity.SourceEntity;

/**
 * Call back between the Source Recycler Adapter to the view creating Recycler
 *
 * @author ranjithsuda
 */
public interface SourceRecyclerCallBack {

  /**
   * Method to be called whwn a source is clicked
   *
   * @param entity -- entity clicked
   */
  void onSourceClicked(SourceEntity entity);
}
