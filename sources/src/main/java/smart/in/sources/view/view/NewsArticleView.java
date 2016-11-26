package smart.in.sources.view.view;

import java.util.ArrayList;

import smart.in.common.service.NWResponseType;
import smart.in.sources.entity.ArticleEntity;

/**
 * MVP View that holds interaction between the News Article presenter and the News Article View
 *
 * @author ranjithsuda
 */

public interface NewsArticleView {

  /**
   * Call back to View , saying to show Error message
   *
   * @param responseType -- response type
   */
  void showErrorMessage(NWResponseType responseType);

  /**
   * Call back to View , to show Sources
   *
   * @param articles -- sources list
   */
  void showSources(ArrayList<ArticleEntity> articles);

  /**
   * Call back to View , whether we want to show progress bar
   *
   * @param show -- whether to show or not
   */
  void showProgressMsg(boolean show);

  /**
   * Method called , when the sort option is clicked
   *
   * @param option -- option
   */
  void onSortOptionClicked(String option);
}
