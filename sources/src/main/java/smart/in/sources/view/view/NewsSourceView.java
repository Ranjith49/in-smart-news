package smart.in.sources.view.view;

import java.util.ArrayList;

import smart.in.common.service.NWResponseType;
import smart.in.sources.entity.SourceEntity;

/**
 * MVP Interface between the View and Presenter layer
 *
 * @author ranjith
 */

public interface NewsSourceView {

  /**
   * Call back to View , saying to show Error message
   *
   * @param responseType -- response type
   */
  void showErrorMessage(NWResponseType responseType);

  /**
   * Call back to View , to show Sources
   *
   * @param sources -- sources list
   */
  void showSources(ArrayList<SourceEntity> sources);

  /**
   * Call back to View , whether we want to show progress bar
   *
   * @param show -- whether to show or not
   */
  void showProgressMsg(boolean show);
}
