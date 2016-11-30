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
import smart.in.sources.db.SourceMemImpl;
import smart.in.sources.entity.SourceEntity;
import smart.in.sources.entity.SourceLogoEntity;
import smart.in.sources.entity.SourceUserEntity;
import smart.in.sources.view.view.SourceRecyclerCallBack;
import smart.in.sources.view.viewholder.SourcesViewHolder;

/**
 * Recycler View Adapter for Sources
 *
 * @author ranjith
 */

public class SourcesRecyclerViewAdapter extends RecyclerView.Adapter<SourcesViewHolder> {

  private ArrayList<SourceEntity> entities;
  private SourceRecyclerCallBack callBack;

  public SourcesRecyclerViewAdapter(ArrayList<SourceEntity> entities,
                                    SourceRecyclerCallBack callBack) {
    this.entities = entities;
    this.callBack = callBack;
  }

  @Override
  public SourcesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View sources_view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_sources_item, parent, false);
    return new SourcesViewHolder(sources_view);
  }

  @Override
  public void onBindViewHolder(SourcesViewHolder holder, int position) {
    final int item_pos = holder.getAdapterPosition();
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

    holder.sourcesLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        callBack.onSourceClicked(entities.get(item_pos));
      }
    });

    SourceUserEntity entity = new SourceUserEntity();
    entity.setId(entities.get(item_pos).getId());
    entity.setName(entities.get(item_pos).getName());
    entity.setUrlImage(entities.get(item_pos).getUrlsToLogos().getSmall());
    if (SourceMemImpl.getInstance().isSourceInProfile(entity)) {
      holder.sourceProfile.setVisibility(View.VISIBLE);
    } else {
      holder.sourceProfile.setVisibility(View.GONE);
    }
  }

  @Override
  public int getItemCount() {
    return entities.size();
  }
}
