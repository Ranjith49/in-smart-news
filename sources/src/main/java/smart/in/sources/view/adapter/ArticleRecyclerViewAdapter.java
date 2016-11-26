package smart.in.sources.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import smart.in.common.helper.DataUtil;
import smart.in.common.helper.Logger;
import smart.in.common.helper.Utils;
import smart.in.sources.R;
import smart.in.sources.entity.ArticleEntity;
import smart.in.sources.view.viewholder.ArticleViewHolder;

/**
 * Article Recycler View Adapter
 *
 * @author ranjithsuda
 */

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

  private final String TAG = ArticleRecyclerViewAdapter.class.getSimpleName();
  private ArrayList<ArticleEntity> entities;

  public ArticleRecyclerViewAdapter(ArrayList<ArticleEntity> entities) {
    this.entities = entities;
  }

  @Override
  public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View articles_view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_articles_item, parent, false);
    return new ArticleViewHolder(articles_view);
  }

  @Override
  public void onBindViewHolder(final ArticleViewHolder holder, int position) {
    final int item_pos = holder.getAdapterPosition();
    if (DataUtil.isEmpty(entities.get(item_pos).getUrlToImage())) {
      holder.articleImage.setVisibility(View.GONE);
    } else {
      Picasso.with(Utils.getAppContext())
          .load(entities.get(item_pos).getUrlToImage())
          .placeholder(R.drawable.newssource)
          .error(R.drawable.newssource)
          .into(holder.articleImage);
      holder.articleImage.setVisibility(View.VISIBLE);
    }

    // Title and Des ..
    holder.articleTitle.setText(entities.get(item_pos).getTitle());
    holder.articleDesc.setText(entities.get(item_pos).getDescription());
    holder.articleDesc.setVisibility(View.GONE);

    if (DataUtil.isEmpty(entities.get(item_pos).getDescription())) {
      holder.articleAction3.setVisibility(View.GONE);
    } else {
      holder.articleAction3.setImageResource(R.drawable.down_outline);
      holder.articleAction3.setVisibility(View.VISIBLE);
    }

    // Author check ..
    if (DataUtil.isEmpty(entities.get(item_pos).getAuthor())) {
      holder.articleAuthor.setVisibility(View.GONE);
    } else {
      holder.articleAuthor.setText(entities.get(item_pos).getAuthor());
      holder.articleAuthor.setVisibility(View.VISIBLE);
    }

    // Actions to perform ..
    holder.articleAction1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d(TAG, "Share Url : -" + entities.get(item_pos).getUrl());
      }
    });

    holder.articleAction2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Logger.d(TAG, "Read More Url : -" + entities.get(item_pos).getUrl());
      }
    });

    holder.articleAction3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (holder.articleDesc.getVisibility() == View.GONE) {
          holder.articleDesc.setVisibility(View.VISIBLE);
          holder.articleAction3.setImageResource(R.drawable.up_outline);
        } else {
          holder.articleDesc.setVisibility(View.GONE);
          holder.articleAction3.setImageResource(R.drawable.down_outline);
        }
      }
    });

  }

  @Override
  public int getItemCount() {
    return entities.size();
  }
}
