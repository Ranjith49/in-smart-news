package smart.in.sources.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;

import smart.in.sources.R;
import smart.in.sources.helper.SourcesConstants;
import smart.in.sources.view.view.NewsArticleView;

/**
 * Dialog Fragment used to show the sort options for an article , if applicable
 *
 * @author ranjithsuda
 */
public class ArticleSortDialogFragment extends DialogFragment {

  String[] items;
  NewsArticleView view;

  public static ArticleSortDialogFragment getInstance(Bundle bundle) {
    ArticleSortDialogFragment fragment = new ArticleSortDialogFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  public void setCallBack(NewsArticleView view) {
    this.view = view;
  }

  @Override
  public Dialog onCreateDialog(Bundle bundle) {
    Bundle b = getArguments();
    ArrayList<String> temp = b.getStringArrayList(SourcesConstants.BUNDLE_ARTICLE_SORT_OPTIONS);
    items = new String[temp.size()];
    items = temp.toArray(items);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(R.string.article_sort_by_title)
        .setItems(items, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            if (view != null) {
              view.onSortOptionClicked(items[which]);
            }
          }
        });
    return builder.create();
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
  }
}
