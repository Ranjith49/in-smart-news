package smart.in.sources.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import smart.in.sources.R;

/**
 * View holder for Sources
 *
 * @author ranjith
 */

public class SourcesViewHolder extends RecyclerView.ViewHolder {

  public RelativeLayout sourcesLayout;
  public TextView sourceTitle;
  public TextView sourceDesc;
  public ImageView sourceIcon;
  public ImageView sourceProfile;

  public SourcesViewHolder(View itemView) {
    super(itemView);
    sourcesLayout = (RelativeLayout) itemView.findViewById(R.id.sources_list_item);
    sourceTitle = (TextView) itemView.findViewById(R.id.sources_list_title);
    sourceDesc = (TextView) itemView.findViewById(R.id.sources_list_desc);
    sourceIcon = (ImageView) itemView.findViewById(R.id.sources_list_icon);
    sourceProfile = (ImageView) itemView.findViewById(R.id.sources_list_in_profile);
  }
}
