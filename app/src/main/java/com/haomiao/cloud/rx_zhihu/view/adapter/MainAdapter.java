package com.haomiao.cloud.rx_zhihu.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haomiao.cloud.rx_zhihu.model.ZhiHuDailyItem;
import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.GlideUtils;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.SpinKitView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.View.Adapter.
 * Created by Cloud on 16/5/24.
 * Instruction
 */
public class MainAdapter extends CBaseRecyclerViewAdapter<ZhiHuDailyItem> {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_DATE = 1;
    public static final int TYPE_FOOTER = 2;
    public static final int STATE_DEFAULT = 5;
    public static final int STATE_LOAD_MORE = 6;
    public static final int STATE_ERROR = 7;

    private OnLoadMoreListener onLoadMoreListener;

    public void setmState(int mState) {
        this.mState = mState;
    }

    private int mState = STATE_DEFAULT;


    public MainAdapter(Context context) {
        super(context);
    }

    private static OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int id);
    }


    public List<ZhiHuDailyItem> getList() {
        return itemList;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (itemList.get(position).getPosition() == 0) {
            return TYPE_DATE;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ViewHolder(v);
        } else if (viewType == TYPE_DATE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_date, parent, false);
            return new DateViewHolder(v);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_footer, parent, false);
            return new FooterViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, itemList.get(position).getId());
                }
            }
        });
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            final ZhiHuDailyItem item = itemList.get(position);
            viewHolder.item_title.setText(item.getTitle());
            GlideUtils.displayImage(item.getImages(), viewHolder.item_pic);
        } else if (holder instanceof DateViewHolder) {
            DateViewHolder viewHolder = (DateViewHolder) holder;
            viewHolder.item_date.setText(itemList.get(position).getDate());
            viewHolder.item_title.setText(itemList.get(position).getTitle());
            GlideUtils.displayImage(itemList.get(position).getImages(), viewHolder.item_pic);
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder viewHolder = (FooterViewHolder) holder;
            if (onLoadMoreListener != null && (mState == STATE_LOAD_MORE)) {
                onLoadMoreListener.onLoadMore();
            }
            viewHolder.loadMoreProgress.setVisibility(View.VISIBLE);
            switch (mState) {
                case STATE_DEFAULT:
                    viewHolder.loadMoreProgress.setVisibility(View.GONE);
                    break;
                case STATE_LOAD_MORE:
                    viewHolder.loadMoreProgress.setVisibility(View.VISIBLE);
                    break;
                case STATE_ERROR:
                    viewHolder.loadMoreProgress.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textView)
        TextView item_title;
        @Bind(R.id.imageView2)
        ImageView item_pic;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


    public static class DateViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_item_date)
        TextView item_date;
        @Bind(R.id.textView)
        TextView item_title;
        @Bind(R.id.imageView2)
        ImageView item_pic;

        public DateViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.spin_kit)
        SpinKitView loadMoreProgress;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public final void setonLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
}
