package smart.in.sources.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import smart.in.sources.R;

/**
 * View Holder holding the Articles
 *
 * @author ranjithsuda
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

  public ImageView articleImage;
  public TextView articleTitle;
  public TextView articleAuthor;
  public TextView articleDesc;
  public TextView articleAction1;
  public TextView articleAction2;
  public ImageView articleAction3;


  public ArticleViewHolder(View itemView) {
    super(itemView);

    articleImage = (ImageView) itemView.findViewById(R.id.article_list_imageview);
    articleTitle = (TextView) itemView.findViewById(R.id.article_list_title);
    articleAuthor = (TextView) itemView.findViewById(R.id.article_list_author);
    articleDesc = (TextView) itemView.findViewById(R.id.article_list_desc);
    articleAction1 = (TextView) itemView.findViewById(R.id.article_list_action1);
    articleAction2 = (TextView) itemView.findViewById(R.id.article_list_action2);
    articleAction3 = (ImageView) itemView.findViewById(R.id.article_list_action3);
  }
}
