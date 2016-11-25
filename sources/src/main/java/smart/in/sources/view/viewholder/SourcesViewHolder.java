package smart.in.sources.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import smart.in.sources.R;

/**
 * View holder for Sources
 *
 * @author ranjith
 */

public class SourcesViewHolder extends RecyclerView.ViewHolder {

  public TextView sourceTitle;
  public TextView sourceDesc;
  public ImageView sourceIcon;

  public SourcesViewHolder(View itemView) {
    super(itemView);
    sourceTitle = (TextView) itemView.findViewById(R.id.sources_list_title);
    sourceDesc = (TextView) itemView.findViewById(R.id.sources_list_desc);
    sourceIcon = (ImageView) itemView.findViewById(R.id.sources_list_icon);
  }
}
