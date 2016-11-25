package smart.in.sources.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import smart.in.common.helper.DataUtil;
import smart.in.common.helper.Utils;
import smart.in.sources.R;
import smart.in.sources.entity.SourceEntity;
import smart.in.sources.entity.SourceLogoEntity;
import smart.in.sources.view.viewholder.SourcesViewHolder;

/**
 * Recycler View Adapter for Sources
 *
 * @author ranjith
 */

public class SourcesRecyclerViewAdapter extends RecyclerView.Adapter<SourcesViewHolder> {

  private ArrayList<SourceEntity> entities;

  public SourcesRecyclerViewAdapter(ArrayList<SourceEntity> entities) {
    this.entities = entities;
  }

  @Override
  public SourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View sources_view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_sources_item, parent, false);
    return new SourcesViewHolder(sources_view);
  }

  @Override
  public void onBindViewHolder(SourcesViewHolder holder, int position) {
    int item_pos = holder.getAdapterPosition();
    holder.sourceTitle.setText(entities.get(item_pos).getName());
    holder.sourceDesc.setText(entities.get(item_pos).getDescription());

    SourceLogoEntity logoEntity = entities.get(item_pos).getUrlsToLogos();
    if (DataUtil.isEmpty(logoEntity.getSmall())) {
      holder.sourceIcon.setImageResource(R.drawable.newssource);
    } else {
      Picasso.with(Utils.getAppContext())
          .load(logoEntity.getSmall())
          .placeholder(R.drawable.newssource)
          .error(R.drawable.newssource)
          .into(holder.sourceIcon);
    }
  }

  @Override
  public int getItemCount() {
    return entities.size();
  }
}
