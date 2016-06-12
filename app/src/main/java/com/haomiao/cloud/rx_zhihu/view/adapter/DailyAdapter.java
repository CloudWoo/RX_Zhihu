package com.haomiao.cloud.rx_zhihu.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.haomiao.cloud.rx_zhihu.R;
import com.haomiao.cloud.rx_zhihu.utils.PicassoUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.haomiao.cloud.rx_zhihu.model.ZhiHuDaily.*;

/**
 *
 * Created by Cloud on 16/5/18.
 */

public class DailyAdapter extends CBaseAdapter<StoriesEntity> {

    public DailyAdapter(Context context) {
        super(context);
    }


    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;
        ViewHolder holder = null;
        if (convertView == null) {
            itemView = inflater.inflate(R.layout.item_view, null);
            holder = new ViewHolder(itemView);
            itemView.setTag(holder);
        } else {
            itemView = convertView;
            holder = (ViewHolder) itemView.getTag();
        }

        holder.textView.setText(itemList.get(position).getTitle());
        PicassoUtils.displayCacheImage(itemList.get(position).getImages().get(0), holder.imageView);
        return itemView;
    }

    static class ViewHolder {
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.textView)
        TextView textView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
